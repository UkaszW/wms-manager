package com.lodz.p.edu.iap.lab.wmsmanager.api.warehouse;

import com.lodz.p.edu.iap.lab.wmsmanager.entity.warehouse.Item;
import com.lodz.p.edu.iap.lab.wmsmanager.entity.warehouse.Warehouse;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

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

    @GetMapping("/{id}")
    public Optional<Warehouse> getById(@PathVariable(value = "id") Long id) {
        return repository.findById(id);
    }

    @PostMapping
    public void save(@RequestBody Warehouse warehouse) {
        repository.save(warehouse);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable(value = "id") Long id) {
        repository.deleteById(id);
    }

    @PutMapping("/{id}")
    public void addItem(@PathVariable(value = "id") Long id, @RequestBody Item item) {
        Warehouse warehouse = repository.findById(id).orElseThrow(IllegalStateException::new);
        warehouse.getItems().add(item);
        repository.save(warehouse);
    }

}
