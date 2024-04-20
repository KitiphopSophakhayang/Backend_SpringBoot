package com.testRestful.restful.repository;

import com.testRestful.restful.entity.OrderItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    List<OrderItem> findByTransactionId(String transactionId);

    @Query(value = "select * from order_item o where o.table_id = :tableId and o.status = 'pending' order by o.order_date desc", nativeQuery = true)
    List<OrderItem> getTableIdAndStatus(Long tableId);

    @Query(value = """
            select
            DAYNAME(oi.order_date) as order_day,
            SUM(oi.total_price) as total_price
            from
                order_item oi
            where
                oi.order_date >= DATE_SUB(CURDATE(), interval (WEEKDAY(CURDATE()) + 7) day)
                and oi.order_date < DATE_SUB(CURDATE(), interval WEEKDAY(CURDATE()) day)
            group by
                DAYNAME(oi.order_date)
            """, nativeQuery = true)
    List<Object[]> getTotalPriceByWeekAndGetDayName();

    @Query(value = "select SUM(oi.total_price) from order_item oi", nativeQuery = true)
    Integer getAllTotalPrice();

    @Query(value = "select COUNT(oi.order_item_id) from order_item oi", nativeQuery = true)
    Integer getAllOrder();

    @Query(value = """
            select
                date(oi.order_date) as order_date,
                SUM(oi.total_price) as total_price
            from
                order_item oi
            where
                oi.order_date >= CURRENT_DATE() - interval 7 day
            group by
                date(oi.order_date);
                """, nativeQuery = true)
    List<Object[]> getTotalPriceByDateInOneWeek();

    @Query(value = """
            select
                m.name as menu_name,
                SUM(oi.quantity) as total_ordered_quantity
            from
                order_item oi
            join order_menu m on
                oi.menu_id = m.menu_id
            where
                m.name != 'น้ำเปล่า'
            group by
                m.menu_id,
                m.name
            order by
                total_ordered_quantity desc
            limit 5;
            """, nativeQuery = true)
    List<Object[]> getTop5MenuList();

    @Query(value = "select * from order_item oi where oi.status = 'pending' limit 10", nativeQuery = true)
    List<OrderItem> getOrderPending();

    @Query(value = "select * from order_item oi where oi.status = 'success' limit 10", nativeQuery = true)
    List<OrderItem> getOrderSuccess();

}
