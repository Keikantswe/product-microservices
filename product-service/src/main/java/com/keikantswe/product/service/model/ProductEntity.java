package com.keikantswe.product.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document(value = "product")
public class ProductEntity {

    @Id
    private String id;
    private String name;
    private String description;
    private double price;
}
