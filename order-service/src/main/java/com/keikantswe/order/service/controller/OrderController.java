package com.keikantswe.order.service.controller;

import com.keikantswe.order.service.dto.OrderRequest;
import com.keikantswe.order.service.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/order")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

//    @PostMapping("/")
//    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
//    @TimeLimiter(name = "inventory")
//    @Retry(name = "inventory")
//    public CompletableFuture<String> placeOrder(@RequestBody OrderRequest orderRequest){
//        log.info("Placing an Order");
//        //fix this line of code
//        //return CompletableFuture.supplyAsync(()-> orderService.placeOrder(orderRequest));
//
//        return CompletableFuture.supplyAsync(()-> "Come back and fix");
//    }

    @PostMapping("/")
    public String placeOrder(@RequestBody OrderRequest orderRequest){
        log.info("Placing an Order");
        orderService.placeOrder(orderRequest);
        return "Order placed successfully";

        //return CompletableFuture.supplyAsync(()-> "Come back and fix");
    }



    public CompletableFuture<String> fallbackMethod(OrderRequest orderRequest, RuntimeException runtimeException){
        return CompletableFuture.supplyAsync(()-> "Something went wrong with the service") ;

    }
}
