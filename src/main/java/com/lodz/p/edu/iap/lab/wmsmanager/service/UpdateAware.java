package com.lodz.p.edu.iap.lab.wmsmanager.service;

public interface UpdateAware<T> {

    void update(Long id, T object);
}
