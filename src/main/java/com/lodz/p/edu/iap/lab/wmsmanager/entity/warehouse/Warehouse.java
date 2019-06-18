package com.lodz.p.edu.iap.lab.wmsmanager.entity.warehouse;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Warehouse {

    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    private String code;
    @NonNull
    private String name;
    @ManyToOne
    @JoinColumn
    @NonNull
    private Address address;
    @OneToMany
    private List<Item> items;
}
