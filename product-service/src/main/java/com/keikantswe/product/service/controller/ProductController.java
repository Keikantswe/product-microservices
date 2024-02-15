package com.keikantswe.product.service.controller;

import com.keikantswe.product.service.dto.ProductRequest;
import com.keikantswe.product.service.dto.ProductResponse;
import com.keikantswe.product.service.model.ProductEntity;
import com.keikantswe.product.service.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/")
    public void createProduct(@RequestBody ProductRequest productRequest){
        log.info("Inside Controller of createProduct");
        productService.createProduct(productRequest);
    }

    @GetMapping("/all")
    public List<ProductResponse> getAllProducts(){
        log.info("Inside controller of getAllProducts");
       return productService.getAllProducts();
    }
}
