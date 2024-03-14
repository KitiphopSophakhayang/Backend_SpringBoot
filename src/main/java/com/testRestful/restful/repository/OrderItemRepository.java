package com.testRestful.restful.repository;

import com.testRestful.restful.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    // คุณสามารถเพิ่มเมธอดเพิ่มเติมที่ต้องการสำหรับการจัดการข้อมูลในตาราง order_item ได้ที่นี่
}
