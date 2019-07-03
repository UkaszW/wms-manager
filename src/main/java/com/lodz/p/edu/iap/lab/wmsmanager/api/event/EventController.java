package com.lodz.p.edu.iap.lab.wmsmanager.api.event;

import com.lodz.p.edu.iap.lab.wmsmanager.entity.event.Event;
import com.lodz.p.edu.iap.lab.wmsmanager.service.EventUpdateService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/event")
@CrossOrigin(origins = "http://localhost:4211")
public class EventController {

    private EventRepository repository;
    private EventUpdateService updateService;

    public EventController(EventRepository repository, EventUpdateService updateService) {
        this.repository = repository;
        this.updateService = updateService;
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
        repository.save(event);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable(value = "id") Long id, @RequestBody Event event) {
        updateService.update(id, event);
    }

    //approval method

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable(value = "id") Long id) {
        repository.deleteById(id);
    }

    @DeleteMapping("/processed")
    public void deleteProcessed() {
        repository.findAll().stream().filter(Event::isProcessed).forEach(event -> repository.deleteById(event.getId()));
    }

}
