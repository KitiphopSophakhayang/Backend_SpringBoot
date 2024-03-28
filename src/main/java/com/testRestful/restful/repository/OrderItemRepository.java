// package com.testRestful.restful.repository;

// import com.testRestful.restful.entity.OrderItem;
// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.stereotype.Repository;

// @Repository
// public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
//     // คุณสามารถเพิ่มเมธอดเพิ่มเติมที่ต้องการสำหรับการจัดการข้อมูลในตาราง order_item ได้ที่นี่
// }

package com.testRestful.restful.repository;

import com.testRestful.restful.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    
    List<OrderItem> findByTransactionId(String transactionId);
    @Query(value = "select * from order_item o where o.table_id = :tableId and o.status = 'pending' order by o.order_date desc", nativeQuery = true)
    // List<OrderItem> getTableIdAndStatus(@Param("tableId") Long tableId);
    List<OrderItem> getTableIdAndStatus(Long tableId);


}
