package com.lodz.p.edu.iap.lab.wmsmanager.api;

import com.lodz.p.edu.iap.lab.wmsmanager.entity.warehouse.Warehouse;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("api/v1/warehouse")
@CrossOrigin(origins = "http://localhost:4211")
public class WarehouseController {

    private WarehouseRepository repository;

    public WarehouseController(WarehouseRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/all")
    public Collection<Warehouse> getAll() {
        return repository.findAll();
    }
}
