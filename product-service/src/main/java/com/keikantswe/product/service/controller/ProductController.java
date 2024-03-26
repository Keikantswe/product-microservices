package com.keikantswe.product.service.controller;

import com.keikantswe.product.service.dto.ProductRequest;
import com.keikantswe.product.service.dto.ProductResponse;
import com.keikantswe.product.service.dto.Response;
import com.keikantswe.product.service.model.ProductEntity;
import com.keikantswe.product.service.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

import static java.time.LocalDateTime.now;

@RestController
@RequestMapping("/api/product")
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/")
    public ResponseEntity<Response> createProduct(@RequestBody ProductRequest productRequest){

        log.info("Inside Controller of createProduct");

        try {

            productService.createProduct(productRequest);

            return ResponseEntity.ok(

                    Response.builder()
                            .timeStamp(now())
                            .statusCode(HttpStatus.CREATED.value())
                            .status(HttpStatus.CREATED)
                            //.data(Collections.singletonMap( productService.createProduct(productRequest)))
                            .message("Product created successfully")
                            .build()
            );
        }
        catch(Exception e){
            log.info("Error adding a product");

            return ResponseEntity.internalServerError()
                    .body(
                            Response.builder()
                                    .timeStamp(now())
                                    .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                                    .message("Failed to add a product, please try again")
                                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                                    .build()
                    );
        }
    }

    @GetMapping("/all")
    public List<ProductResponse> getAllProducts(){
        log.info("Inside controller of getAllProducts");
       return productService.getAllProducts();
    }
}
