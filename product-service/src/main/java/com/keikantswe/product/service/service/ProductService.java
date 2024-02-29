package com.keikantswe.product.service.service;

import com.keikantswe.product.service.dto.ProductRequest;
import com.keikantswe.product.service.dto.ProductResponse;
import com.keikantswe.product.service.model.ProductEntity;
import com.keikantswe.product.service.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void createProduct(ProductRequest productRequest) {

        ProductEntity productEntity = ProductEntity
                .builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();

        productRepository.save(productEntity);
    }

    public List<ProductResponse> getAllProducts() {
        List<ProductEntity> product = productRepository.findAll();

        //Map the data to the Response, Method is on "Helper Method."
        return product.stream().map(productEntity -> mapToProductResponse(productEntity)).toList();
    }

    //======================================Helper Methods===============================================================================

    // Map the data from database to The Response
    private ProductResponse mapToProductResponse(ProductEntity productEntity) {
       return    ProductResponse
                .builder()
                .id(productEntity.getId())
                .name(productEntity.getName())
                .description(productEntity.getDescription())
                .price(productEntity.getPrice())
                .build();
    }
}
