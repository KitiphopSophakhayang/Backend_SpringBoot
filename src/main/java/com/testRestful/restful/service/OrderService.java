package com.testRestful.restful.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testRestful.restful.entity.Order;
import com.testRestful.restful.repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;
    
    public Order saveOrder(Order order){
        return repository.save(order);
    }

      public List<Order> saveOrders(List<Order> orders){
        return repository.saveAll(orders);
    }

    public List<Order> getOrders(){
        return repository.findAll();
    }

    public Order getOrdersById(int id){
        return repository.findById(id).orElse( null);
    }

     public Order getOrdersByName(String name){
        return repository.findByName(name);
    }


    public String deleteOrder(int id){
        repository.deleteById(id);
        return "Order removed !" + id;
    }


    public Order updatOrder(Order order){
        Order existingOrder = repository.findById(order.getId()).orElse(null);
        existingOrder.setName(order.getName());
        existingOrder.setTpye_menu(order.getTpye_menu());
        existingOrder.setPrice(order.getPrice());
        existingOrder.setImage(order.getImage());
        return repository.save(existingOrder);
    }


}
