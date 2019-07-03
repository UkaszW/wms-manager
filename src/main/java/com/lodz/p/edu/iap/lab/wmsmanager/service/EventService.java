package com.lodz.p.edu.iap.lab.wmsmanager.service;

import java.util.Optional;

import com.lodz.p.edu.iap.lab.wmsmanager.api.event.EventRepository;
import com.lodz.p.edu.iap.lab.wmsmanager.api.item.ItemRepository;
import com.lodz.p.edu.iap.lab.wmsmanager.api.warehouse.WarehouseRepository;
import com.lodz.p.edu.iap.lab.wmsmanager.entity.event.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EventService implements ExecutionAware<Event>, UpdateAware<Event> {

    private final EventRepository eventRepository;
    private final ItemRepository itemRepository;
    private final WarehouseRepository warehouseRepository;

    public EventService(EventRepository eventRepository, ItemRepository itemRepository, WarehouseRepository warehouseRepository) {
        this.eventRepository = eventRepository;
        this.itemRepository = itemRepository;
        this.warehouseRepository = warehouseRepository;
    }

    @Override
    public void execute(Event object) {
        switch (object.getAction()) {
            case "ADD":
                AddEvent addEvent = (AddEvent) object;
                if (Type.ITEM.name().equals(addEvent.getType())) {

                }
                break;
            case "DELETE":
                DeleteEvent deleteEvent = (DeleteEvent) object;
                if (Type.ITEM.name().equals(deleteEvent.getType())) {
                    itemRepository.deleteById(deleteEvent.getObjectId());
                } else if (Type.WAREHOUSE.name().equals(deleteEvent.getType())) {
                    warehouseRepository.deleteById(deleteEvent.getObjectId());
                }
                break;
            case "TRANSFER":
                TransferEvent transferEvent = (TransferEvent) object;
                itemRepository.findById(transferEvent.getObjectId()).ifPresentOrElse(item -> {
                    Optional.ofNullable(transferEvent.getDestinationWarehouse()).ifPresentOrElse(warehouse -> {
                        item.setWarehouse(warehouse);
                        itemRepository.save(item);
                    }, () -> log.error("Error occurred - destination warehouse not exists!"));
                }, () -> log.error("Item with id: {} not exist!", transferEvent.getObjectId()));
                break;
            default:
                log.error("Unrecognized event type");
        }
    }

    @Override
    public void update(Long id, Event object) {
        eventRepository.findById(id).ifPresentOrElse(event -> {
            event.setObjectId(object.getObjectId());
            event.setStatus(object.getStatus());
            event.setProcessed(object.isProcessed());
            event.setAccepted(object.isAccepted());
            event.setRead(object.isRead());
            event.setComment(object.getComment());

            eventRepository.save(event);
        }, () -> log.error("Event with id: {} not exist!", id));
    }

}
