package com.lodz.p.edu.iap.lab.wmsmanager.entity.warehouse;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@Data
public class Item {

    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    private String code;
    @NonNull
    private String name;
    @NonNull
    private Long quantity;
    @ManyToOne
    @JoinColumn
    private Status status;
}
