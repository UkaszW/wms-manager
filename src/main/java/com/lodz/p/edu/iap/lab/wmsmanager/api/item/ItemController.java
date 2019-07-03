package com.lodz.p.edu.iap.lab.wmsmanager.api.item;

import com.lodz.p.edu.iap.lab.wmsmanager.entity.warehouse.Item;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/warehouse/item")
@CrossOrigin(origins = "http://localhost:4211")
public class ItemController {

    private ItemRepository repository;

    public ItemController(ItemRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/all")
    public Collection<Item> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Item> getById(@PathVariable(value = "id") Long id) {
        return repository.findById(id);
    }

    @GetMapping("/{externalId}")
    public Optional<Item> getByExternalId(@PathVariable(value = "externalId") String externalId) {
        return repository.findAll().stream().filter(event -> externalId.equals(event.getExternalId())).findFirst();
    }

    @PostMapping
    public void save(@RequestBody Item item) {
        repository.save(item);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable(value = "id") Long id) {
        repository.deleteById(id);
    }
}
