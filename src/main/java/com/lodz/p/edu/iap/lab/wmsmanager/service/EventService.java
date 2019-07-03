package com.lodz.p.edu.iap.lab.wmsmanager.service;

import com.lodz.p.edu.iap.lab.wmsmanager.api.event.EventRepository;
import com.lodz.p.edu.iap.lab.wmsmanager.entity.event.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EventService implements ExecutionAware<Event>, UpdateAware<Event> {

    private final EventRepository repository;

    public EventService(EventRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(Event object) {
        switch (object.getAction()) {
            case "ADD":
                AddEvent addEvent = (AddEvent) object;

                break;
            case "DELETE":
                DeleteEvent deleteEvent = (DeleteEvent) object;


                break;
            case "TRANSFER":
                TransferEvent transferEvent = (TransferEvent) object;


                break;
            default:
                log.error("Unrecognized event type");
        }
    }

    @Override
    public void update(Long id, Event object) {
        repository.findById(id).ifPresentOrElse(event -> {
            event.setObjectId(object.getObjectId());
            event.setStatus(object.getStatus());
            event.setProcessed(object.isProcessed());
            event.setAccepted(object.isAccepted());
            event.setRead(object.isRead());
            event.setComment(object.getComment());

            repository.save(event);
        }, () -> log.error("Event with id: {} not exist!", id));
    }

}
