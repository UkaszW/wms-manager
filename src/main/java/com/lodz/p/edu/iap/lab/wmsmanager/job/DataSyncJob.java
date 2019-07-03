package com.lodz.p.edu.iap.lab.wmsmanager.job;

import com.lodz.p.edu.iap.lab.wmsmanager.api.event.EventController;
import com.lodz.p.edu.iap.lab.wmsmanager.entity.BaseEntity;
import com.lodz.p.edu.iap.lab.wmsmanager.entity.event.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
public class DataSyncJob {

    private static final String EVENT_SYNC_URL = "http://localhost:8080/api/v1/event/all";

    private final RestTemplate restTemplate = new RestTemplate();

    private final EventController eventController;

    public DataSyncJob(EventController eventController) {
        this.eventController = eventController;
    }

    // "0 0 * * * *" every hour
    // "0 0/5 0 ? * *" every 5 minutes
    // "*/10 * * * * *" every 10 seconds
    @Scheduled(cron = "0 0/5 0 ? * *")
    private void syncEvents() {
        log.info("Date synchronization: " + Instant.now());

        try {
            ResponseEntity<Collection<Event>> responseEntity = restTemplate
                    .exchange(EVENT_SYNC_URL, HttpMethod.GET, null, new ParameterizedTypeReference<Collection<Event>>() {});

            Collection<Event> events = responseEntity.getBody();
            update(events);

        } catch (Exception e) {
            log.error("Error occurred: ", e);
        }
    }

    private void update(Collection<Event> events) {
        if (!CollectionUtils.isEmpty(events)) {
            consumeAddition(events);
            consumeDeletion(events);
        }
    }

    private void consumeAddition(Collection<Event> events) {
        events.stream().filter(BaseEntity::isAddedOrUpdated).forEach(event -> eventController.getByExternalId(event.getExternalId()).ifPresentOrElse(
                existingEvent -> eventController.update(existingEvent.getId(), event), () -> eventController.save(event)));
    }

    private void consumeDeletion(Collection<Event> events) {
        Set<String> externalEventsIds = events.stream().map(BaseEntity::getExternalId).collect(Collectors.toSet());
        Set<String> internalEventsIds = eventController.getAll().stream().map(BaseEntity::getExternalId).collect(Collectors.toSet());
        internalEventsIds.removeAll(externalEventsIds);
        internalEventsIds.forEach(eventController::deleteByExternalId);
    }

}
