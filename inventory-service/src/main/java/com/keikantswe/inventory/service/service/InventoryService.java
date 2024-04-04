package com.keikantswe.inventory.service.service;

import com.keikantswe.inventory.service.dto.InventoryResponse;
import com.keikantswe.inventory.service.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public List<InventoryResponse> isInStock(List<String> barcode) {
        return inventoryRepository.findByBarcodeIn(barcode).stream()
                .map(inventory ->
                    InventoryResponse.builder()
                            .barcode(inventory.getBarcode())
                            .isInStock(inventory.getQuantity() > 0)
                            .build()
                ).toList();
    }
}
