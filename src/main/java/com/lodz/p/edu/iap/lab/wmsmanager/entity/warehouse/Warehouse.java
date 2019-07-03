package com.lodz.p.edu.iap.lab.wmsmanager.entity.warehouse;

import com.lodz.p.edu.iap.lab.wmsmanager.entity.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.Set;

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
}
