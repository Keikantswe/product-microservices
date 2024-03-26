package com.keikantswe.order.service.service;

import com.keikantswe.order.service.dto.OrderLineItemsDto;
import com.keikantswe.order.service.dto.OrderRequest;
import com.keikantswe.order.service.model.OrderEntity;
import com.keikantswe.order.service.model.OrderLineItems;
import com.keikantswe.order.service.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

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

        orderRepository.save(order);
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
