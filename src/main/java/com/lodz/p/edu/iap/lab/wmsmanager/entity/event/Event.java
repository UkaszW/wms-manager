package com.lodz.p.edu.iap.lab.wmsmanager.entity.event;

import com.lodz.p.edu.iap.lab.wmsmanager.entity.warehouse.Warehouse;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Data
@NoArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull
    private Action action;
    @NonNull
    private Type type;
    @NonNull
    private Long objectId;
    @NonNull
    private String message;
    @NonNull
    @ManyToOne
    @JoinColumn
    private Warehouse source;
    @ManyToOne
    @JoinColumn
    private Warehouse destination;
    @NonNull
    private boolean processed = false;
    @NonNull
    private boolean accepted = false;
    private String comment;
    @NonNull
    private Instant creationDate;
}
