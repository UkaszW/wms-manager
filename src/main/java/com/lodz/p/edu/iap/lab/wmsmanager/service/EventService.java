package com.lodz.p.edu.iap.lab.wmsmanager.service;

import com.lodz.p.edu.iap.lab.wmsmanager.api.event.EventRepository;
import com.lodz.p.edu.iap.lab.wmsmanager.entity.event.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EventService {

    protected final EventRepository repository;

    public EventService(EventRepository repository) {
        this.repository = repository;
    }

    public void execute(Event event) {
        switch (event.getAction()) {
            case "ADD":
                AddEvent addEvent = (AddEvent) event;

                break;
            case "DELETE":
                DeleteEvent deleteEvent = (DeleteEvent) event;


                break;
            case "TRANSFER":
                TransferEvent transferEvent = (TransferEvent) event;


                break;
            default:
                log.error("Unrecognized event type");
        }
    }
}
