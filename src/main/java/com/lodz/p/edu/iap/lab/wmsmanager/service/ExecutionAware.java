package com.lodz.p.edu.iap.lab.wmsmanager.service;

public interface ExecutionAware<T> {

    void execute(T object);
}
