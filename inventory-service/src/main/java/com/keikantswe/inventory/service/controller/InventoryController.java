package com.keikantswe.inventory.service.controller;

import com.keikantswe.inventory.service.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/{barcode}")
    public boolean isInStock(@PathVariable("barcode") String barcode){
        return inventoryService.isInStock(barcode);
    }
}
