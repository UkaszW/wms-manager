package com.lodz.p.edu.iap.lab.wmsmanager.service;

import com.lodz.p.edu.iap.lab.wmsmanager.api.event.EventRepository;
import com.lodz.p.edu.iap.lab.wmsmanager.entity.event.Event;
import org.springframework.stereotype.Service;

@Service
public class EventUpdateService extends EventService implements UpdateAware<Event> {

    public EventUpdateService(EventRepository repository) {
        super(repository);
    }

    @Override
    public void update(Long id, Event object) {
        repository.findById(id).ifPresent(event -> {
            event.setAccepted(object.isAccepted());
            // ToDo: must be finish
            repository.save(event);
        });
    }
}
