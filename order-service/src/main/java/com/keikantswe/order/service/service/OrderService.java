package com.keikantswe.order.service.service;

import com.keikantswe.order.service.dto.InventoryResponse;
import com.keikantswe.order.service.dto.OrderLineItemsDto;
import com.keikantswe.order.service.dto.OrderRequest;
import com.keikantswe.order.service.model.OrderEntity;
import com.keikantswe.order.service.model.OrderLineItems;
import com.keikantswe.order.service.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private WebClient webClient;

    public void placeOrder(OrderRequest orderRequest) {

        //Creating a new order
        OrderEntity order = new OrderEntity();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems = orderRequest
                .getOrderLineItemsDto()
                .stream()
                .map(orderLineItemsDto -> mapToOrderDto(orderLineItemsDto))
                .toList();

        order.setOrderLineItems(orderLineItems);

        List<String> barcode = order.getOrderLineItems()
                .stream()
                .map(OrderLineItems -> OrderLineItems.getBarcode())
                .toList();

        InventoryResponse[] inventoryResponsesArray = webClient.get()
                .uri("http://localhost:8082/api/inventory")
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();

        boolean allProduct = Arrays.stream(inventoryResponsesArray)
                .allMatch(InventoryResponse -> InventoryResponse.isInStock());

        if(allProduct){
            orderRepository.save(order);
        }else {
            throw new IllegalArgumentException("Product out of stock");
        }

    }

    //=========================================Helper Methods=======================================================================
    private OrderLineItems mapToOrderDto(OrderLineItemsDto orderLineItemsDto) {

        OrderLineItems order = new OrderLineItems();
        order.setBarcode(orderLineItemsDto.getBarcode());
        order.setQuantity(orderLineItemsDto.getQuantity());
        order.setPrice(orderLineItemsDto.getPrice());

        return order;

    }
}
