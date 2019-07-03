package com.lodz.p.edu.iap.lab.wmsmanager.entity.warehouse;

import javax.persistence.*;
import java.util.Set;

import com.lodz.p.edu.iap.lab.wmsmanager.entity.BaseEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Warehouse extends BaseEntity {

    private String code;
    private String name;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Address address;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "warehouse")
    private Set<Item> items;

    @Builder
    public Warehouse(Long id, String externalId, boolean addedOrUpdated, String code, String name, Address address, Set<Item> items) {
        super(id, externalId, addedOrUpdated);
        this.code = code;
        this.name = name;
        this.address = address;
        this.items = items;
    }
}
