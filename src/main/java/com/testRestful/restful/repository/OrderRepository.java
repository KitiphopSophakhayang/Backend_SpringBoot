package com.testRestful.restful.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.testRestful.restful.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    Order findByName(String name);

    @Query(value = "select count(om.menu_id) from order_menu om", nativeQuery = true)
    Integer getAllMenu();

    @Query(value = """
            select om.*
            from order_menu om
            where om.food_type = :id
            """, nativeQuery = true)
    List<Order> getByfoodTypes(Integer id);

}