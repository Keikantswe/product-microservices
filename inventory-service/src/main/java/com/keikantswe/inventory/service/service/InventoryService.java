package com.keikantswe.inventory.service.service;

import com.keikantswe.inventory.service.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public boolean isInStock(String barcode) {
        return inventoryRepository.findByBarcode(barcode).isPresent();
    }
}
