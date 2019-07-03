package com.lodz.p.edu.iap.lab.wmsmanager.entity.event;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ADD")
@Data
@NoArgsConstructor
public class AddEvent extends Event {

    private Long quantity;
}
