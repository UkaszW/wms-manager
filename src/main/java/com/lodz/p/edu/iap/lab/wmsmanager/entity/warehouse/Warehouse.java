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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull
    private String code;
    @NonNull
    private String name;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    @NonNull
    private Address address;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Item> items;
}
