package com.testRestful.restful.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.testRestful.restful.entity.Order;

public interface OrderRepository  extends JpaRepository<Order,Integer>{
    Order findByName(String name);

    @Query(value = "select count(om.menu_id) from order_menu om", nativeQuery = true)
    Integer getAllMenu();
    
}