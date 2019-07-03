package com.lodz.p.edu.iap.lab.wmsmanager.entity.event;

import com.lodz.p.edu.iap.lab.wmsmanager.entity.BaseEntity;
import com.lodz.p.edu.iap.lab.wmsmanager.entity.warehouse.Warehouse;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="action", discriminatorType = DiscriminatorType.STRING)
@Data
@NoArgsConstructor
public class Event extends BaseEntity {

    @Column(insertable = false, updatable = false)
    private String action;
    private String type;
    private Long objectId;
    private String status;
    private boolean processed = false;
    private boolean accepted = false;
    private boolean read = false;
    private String comment;
    private Instant creationDate;
}
