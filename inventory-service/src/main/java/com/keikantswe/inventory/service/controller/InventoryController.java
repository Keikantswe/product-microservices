package com.keikantswe.inventory.service.controller;

import com.keikantswe.inventory.service.dto.InventoryResponse;
import com.keikantswe.inventory.service.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping
    public List<InventoryResponse> isInStock(@RequestParam List<String> barcode){
        return inventoryService.isInStock(barcode);
    }
}
