package com.keikantswe.inventory.service;

import com.keikantswe.inventory.service.model.InventoryEntity;
import com.keikantswe.inventory.service.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRepository){
		return args -> {
			InventoryEntity inventory = new InventoryEntity();
			inventory.setQuantity(100);
			inventory.setBarcode("Iphone_13");

			InventoryEntity inventory1 = new InventoryEntity();
			inventory1.setQuantity(1);
			inventory1.setBarcode("Iphone_12");

			inventoryRepository.save(inventory);
			inventoryRepository.save(inventory1);
		};
	}

}
