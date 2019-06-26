package com.lodz.p.edu.iap.lab.wmsmanager.api.warehouse;

import com.lodz.p.edu.iap.lab.wmsmanager.entity.warehouse.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
}
