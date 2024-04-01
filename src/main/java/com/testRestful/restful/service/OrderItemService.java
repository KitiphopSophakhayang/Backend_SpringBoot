package com.testRestful.restful.service;

import com.testRestful.restful.entity.OrderItem;
import com.testRestful.restful.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;

    @Autowired
    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    public void saveOrderItems(OrderItem[] orderItems, String transactionId) {
        for (OrderItem orderItem : orderItems) {
            orderItem.setTransactionId(transactionId); // กำหนด transactionId ให้กับทุกรายการอาหาร
            orderItemRepository.save(orderItem);
        }
    }

    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }

    public List<OrderItem> getOrderItemsByTransactionId(String transactionId) {
        return orderItemRepository.findByTransactionId(transactionId);
    }

    public List<OrderItem> getTableIdAndStatus(Long tableId) {
        return orderItemRepository.getTableIdAndStatus(tableId);
    }

    public Object getTotalPrice() {
        Object result = orderItemRepository.getTotalPrice();
        Map<String, Object> dayTotalPrice = new HashMap<>();
        dayTotalPrice.put("result", result);
        return dayTotalPrice;
    }

    public Integer getAllTotalPrice() {
        return orderItemRepository.getAllTotalPrice();
    }
    
    public Integer getAllOrder() {
        return orderItemRepository.getAllOrder();
    }
}
