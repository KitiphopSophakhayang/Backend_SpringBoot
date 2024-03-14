package com.testRestful.restful.service;

import com.testRestful.restful.entity.OrderItem;
import com.testRestful.restful.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;

    @Autowired
    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    public void saveOrderItems(OrderItem[] orderItems) {
        for (OrderItem orderItem : orderItems) {
            orderItemRepository.save(orderItem);
        }
    }
}
