package com.lodz.p.edu.iap.lab.wmsmanager.entity.event;

import lombok.Data;

public enum Status {

    SUBMIT_FOR_APPROVAL("Submit for approval"),
    APPROVED("Approved"),
    REJECTED("Rejected");

    private String name;

    private Status(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
