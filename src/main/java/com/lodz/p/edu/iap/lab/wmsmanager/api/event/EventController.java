package com.lodz.p.edu.iap.lab.wmsmanager.api.event;

import java.time.Instant;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import com.lodz.p.edu.iap.lab.wmsmanager.entity.event.Event;
import com.lodz.p.edu.iap.lab.wmsmanager.service.EventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/v1/event")
@CrossOrigin(origins = "http://localhost:4211")
public class EventController {

    private final EventRepository repository;
    private final EventService eventService;

    public EventController(EventRepository repository, EventService eventService) {
        this.repository = repository;
        this.eventService = eventService;
    }

    @GetMapping("/all")
    public Collection<Event> getAll() {
        return repository.findAll();
    }

    @GetMapping("/processed")
    public Collection<Event> getProcessed() {
        return repository.findAll().stream().filter(Event::isProcessed).collect(Collectors.toList());
    }

    @GetMapping("/unprocessed")
    public Collection<Event> getUnprocessed() {
        return repository.findAll().stream().filter(event -> !event.isProcessed()).collect(Collectors.toList());
    }

    @GetMapping("/read")
    public Collection<Event> getRead() {
        return repository.findAll().stream().filter(Event::isRead).collect(Collectors.toList());
    }

    @GetMapping("/unread")
    public Collection<Event> getUnread() {
        return repository.findAll().stream().filter(event -> !event.isRead()).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public Optional<Event> getById(@PathVariable(value = "id") Long id) {
        return repository.findById(id);
    }

    @GetMapping("/{externalId}")
    public Optional<Event> getByExternalId(@PathVariable(value = "externalId") String externalId) {
        return repository.findAll().stream().filter(event -> externalId.equals(event.getExternalId())).findFirst();
    }

    @PostMapping
    public void save(@RequestBody Event event) {
        event.setCreationDate(Instant.now());
        repository.save(event);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable(value = "id") Long id, @RequestBody Event event) {
        eventService.update(id, event);
    }

    @PutMapping("/{id}/approve/{isApproved}")
    public void approve(@PathVariable(value = "id") Long id, @PathVariable(value = "isApproved") boolean isApproved) {
        getById(id).ifPresentOrElse(event -> {
            event.setAccepted(isApproved);
            event.setProcessed(true);
            if (isApproved) {
                event.setAccepted(true);
                eventService.execute(event);
            }
        }, () -> log.error("Event with id: {} not exist!", id));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable(value = "id") Long id) {
        repository.deleteById(id);
    }

    @DeleteMapping("/{externalId}")
    public void deleteByExternalId(@PathVariable(value = "extenalId") String externalId) {
        repository.findAll().stream().filter(event -> externalId.equals(event.getExternalId())).findFirst().ifPresentOrElse(event -> {
            repository.deleteById(event.getId());
        }, () -> log.error("Event with external id: {} not exist!", externalId));
    }

    @DeleteMapping("/processed")
    public void deleteProcessed() {
        repository.findAll().stream().filter(Event::isProcessed).forEach(event -> repository.deleteById(event.getId()));
    }

}
