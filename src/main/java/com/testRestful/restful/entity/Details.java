// Details.java
package com.testRestful.restful.entity;

import java.math.BigDecimal; // นำเข้า BigDecimal ที่จำเป็น

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "details")
public class Details {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long detailId;

    private String detailname;

    private BigDecimal price; // เปลี่ยนเป็น BigDecimal เพื่อเก็บราคา

    // constructors, getters, setters
}
