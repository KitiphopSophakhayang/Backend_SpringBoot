package com.testRestful.restful.repository;

import com.testRestful.restful.entity.DiningTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiningTableRepository extends JpaRepository<DiningTable, Long> {
    // คุณสามารถเพิ่มเมธอดเพิ่มเติมที่ต้องการสำหรับการจัดการข้อมูลในตาราง tables ได้ที่นี่
}
