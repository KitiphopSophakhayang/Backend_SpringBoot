package com.testRestful.restful.repository;

import com.testRestful.restful.entity.Details;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailsRepository extends JpaRepository<Details, Long> {
    // คุณสามารถเพิ่มเมทอดเพิ่มเติมได้ตามความต้องการ
}
