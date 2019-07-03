package com.lodz.p.edu.iap.lab.wmsmanager.entity.warehouse;

import com.lodz.p.edu.iap.lab.wmsmanager.entity.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
public class ItemType extends BaseEntity {

    private String code;
    private String name;
}
