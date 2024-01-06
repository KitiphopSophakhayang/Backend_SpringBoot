package com.testRestful.restful.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.testRestful.restful.entity.Order;

public interface OrderRepository  extends JpaRepository<Order,Integer>{
    Order findByName(String name);
    
}
