package com.lodz.p.edu.iap.lab.wmsmanager.api.item;

import com.lodz.p.edu.iap.lab.wmsmanager.entity.warehouse.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ItemRepository extends JpaRepository<Item, Long> {
}
