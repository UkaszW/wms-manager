package com.lodz.p.edu.iap.lab.wmsmanager.job;

import com.lodz.p.edu.iap.lab.wmsmanager.api.event.EventController;
import com.lodz.p.edu.iap.lab.wmsmanager.entity.event.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.Instant;
import java.util.Collection;
import java.util.Collections;

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
    // "0 0/15 * 1/1 * ? *" every 15 minutes
    // "*/10 * * * * *" = every 10 seconds
    @Scheduled(cron = "0 0/15 * 1/1 * ? *")
    private void syncEvents() {
        log.info("Date synchronization: " + Instant.now());

        try {
            ResponseEntity<Collection<Event>> responseEntity = restTemplate
                    .exchange(EVENT_SYNC_URL, HttpMethod.GET, null, new ParameterizedTypeReference<Collection<Event>>() {
                    });

            Collection<Event> events = responseEntity.getBody();
            if (!CollectionUtils.isEmpty(events)) {
                events.forEach(event -> {
                    if (event.isAddedOrUpdated()) {
                        eventController.getByExternalId(event.getExternalId()).ifPresent(existingEvent -> {
                            eventController.update(existingEvent.getId(), event);
                        });

                    }
                });
            }
        } catch (Exception e) {
            log.error("Error occurred: ", e);
        }
    }
}
