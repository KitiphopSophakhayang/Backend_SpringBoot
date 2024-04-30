package com.testRestful.restful.repository;

import com.testRestful.restful.entity.OrderItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

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

    @Query(value = "select * from order_item oi where oi.status = 'pending' ", nativeQuery = true)
    List<OrderItem> getOrderPending();

    @Query(value = "select * from order_item oi where oi.status = 'success' ", nativeQuery = true)
    List<OrderItem> getOrderSuccess();

    @Query(value = "SELECT oi.order_item_id, oi.order_date, oi.quantity, oi.transaction_id, oi.status, oi.total_price, om.name, om.price "
            +
            "FROM order_item oi INNER JOIN order_menu om ON om.menu_id = oi.menu_id " +
            "WHERE oi.table_id = :tableId AND (oi.status = :status)", nativeQuery = true)
    List<Object[]> getOrderItemsFormatted(@Param("tableId") Long tableId, @Param("status") String status);

    // @Query(value = """
    //                 SELECT
    //                 oi.order_item_id,
    //                 oi.transaction_id,	
    //                 oi.payment_status,
    //                 oi.order_date,
    //                 GROUP_CONCAT(om.name) AS menu_names,
    //                 oi.table_id,
    //                 GROUP_CONCAT(oi.quantity) AS quantities,
    //                 GROUP_CONCAT(oi.status) AS statuses,
    //                 oi.total_price
    //                 FROM
    //                     order_item oi
    //                 LEFT JOIN
    //                     order_menu om ON oi.menu_id = om.menu_id
    //                 WHERE
    //                     oi.transaction_id IN (
    //                         SELECT
    //                             oi.transaction_id
    //                         FROM
    //                             order_item oi
    //                         GROUP BY
    //                             oi.transaction_id
    //                         HAVING
    //                             COUNT(oi.transaction_id) > 1 OR COUNT(oi.transaction_id) = 1
    //                     )
    //                     AND (oi.status = 'pending' OR oi.status = 'success')
    //                 GROUP BY
    //                     oi.transaction_id;
    //         """, nativeQuery = true)
    // List<Object[]> getGroupedOrderItems();

    @Query(value = """
        select
                    group_concat(oi.order_item_id) as order_item_id ,
                    oi.transaction_id,
                    oi.payment_status,
                    oi.order_date,
                    GROUP_CONCAT(om.name) as menu_names,
                    oi.table_id,
                    GROUP_CONCAT(oi.quantity) as quantities,
                    GROUP_CONCAT(oi.status) as statuses,
                    oi.total_price
                from
                    order_item oi
                left join
                                        order_menu om on
                    oi.menu_id = om.menu_id
                where
                    oi.transaction_id in (
                    select
                        oi.transaction_id
                    from
                        order_item oi
                    group by
                        oi.transaction_id
                    having
                        COUNT(oi.transaction_id) > 1
                            or COUNT(oi.transaction_id) = 1
                                        )
                    and (oi.status = 'pending'
                        or oi.status = 'success')
                group by
                    oi.transaction_id;          
""", nativeQuery = true)
List<Object[]> getGroupedOrderItems();

    @Query(value = "SELECT " +
            "oi.transaction_id, " +
            "oi.order_date, " +
            "GROUP_CONCAT(om.name) AS menu_names, " +
            "oi.table_id, " +
            "GROUP_CONCAT(oi.quantity) AS quantities, " +
            "GROUP_CONCAT(oi.status) AS statuses, " +
            "oi.total_price, " +
            "oi.payment_status " +
            "FROM order_item oi " +
            "LEFT JOIN order_menu om ON oi.menu_id = om.menu_id " +
            "WHERE oi.transaction_id IN ( " +
            "SELECT oi.transaction_id " +
            "FROM order_item oi " +
            "GROUP BY oi.transaction_id " +
            "HAVING COUNT(oi.transaction_id) > 1 OR COUNT(oi.transaction_id) = 1) " +
            "AND (oi.status = 'pending' OR oi.status = 'success') " +
            "AND oi.payment_status = 'complete' " +
            "GROUP BY oi.transaction_id " +
            "ORDER BY oi.order_date ASC", nativeQuery = true)
    List<Object[]> getCompleteGroupedOrderItems();
}
