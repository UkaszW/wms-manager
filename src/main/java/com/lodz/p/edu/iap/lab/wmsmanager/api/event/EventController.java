package com.lodz.p.edu.iap.lab.wmsmanager.api.event;

import com.lodz.p.edu.iap.lab.wmsmanager.entity.event.Event;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/event")
@CrossOrigin(origins = "http://localhost:4211")
public class EventController {

    private EventRepository repository;

    public EventController(EventRepository repository) {
        this.repository = repository;
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

    @GetMapping("/{id}")
    public Optional<Event> getById(@PathVariable(value = "id") Long id) {
        return repository.findById(id);
    }

    @PostMapping
    public void save(@RequestBody Event event) {
        repository.save(event);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable(value = "id") Long id) {
        repository.deleteById(id);
    }

    @DeleteMapping("/processed")
    public void deleteProcessed() {
        repository.findAll().stream().filter(Event::isProcessed).forEach(event -> repository.deleteById(event.getId()));
    }


}
