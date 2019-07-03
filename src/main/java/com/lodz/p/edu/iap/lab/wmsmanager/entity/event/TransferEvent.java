package com.lodz.p.edu.iap.lab.wmsmanager.entity.event;

import com.lodz.p.edu.iap.lab.wmsmanager.entity.warehouse.Warehouse;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("TRANSFER")
@Data
@NoArgsConstructor
public class TransferEvent extends Event {

    private Long quantity;
    @ManyToOne
    @JoinColumn
    private Warehouse destinationWarehouse;
}
