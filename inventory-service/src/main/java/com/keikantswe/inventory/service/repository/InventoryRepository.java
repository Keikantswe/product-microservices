package com.keikantswe.inventory.service.repository;

import com.keikantswe.inventory.service.model.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryEntity, Long> {
    List<InventoryEntity> findByBarcodeIn(List<String> barcode);
}
