package com.lodz.p.edu.iap.lab.wmsmanager.entity.warehouse;

import com.lodz.p.edu.iap.lab.wmsmanager.entity.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Data
@NoArgsConstructor
public class Item extends BaseEntity {

    @ManyToOne
    @JoinColumn
    private ItemType itemType;
    private Long quantity;
    @ManyToOne
    @JoinColumn
    private Warehouse warehouse;
}
