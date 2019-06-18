package com.lodz.p.edu.iap.lab.wmsmanager.entity.warehouse;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    private String street;
    @NonNull
    private Long number;
    @NonNull
    private String postalCode;
    @NonNull
    private String city;
}
