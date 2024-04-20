package com.testRestful.restful.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.testRestful.restful.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    Order findByName(String name);

    @Query(value = "select count(om.menu_id) from order_menu om", nativeQuery = true)
    Integer getAllMenu();

    // @Query(value = "select * from order_menu where food_type = :id ", nativeQuery
    // = true)
    // List<Order> getByfoodTypes(Integer id);

    @Query(value = """
            select om.*
            from order_menu om
            inner join order_item oi on om.menu_id = oi.menu_id
            where om.food_type = :id
            """, nativeQuery = true)
    List<Order> getByfoodTypes(Integer id);

}