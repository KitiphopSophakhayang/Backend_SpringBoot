// OrderItem.java
package com.testRestful.restful.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_item")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderItemId;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Order order;

    private Date orderDate;

    private BigDecimal totalPrice;

    @ManyToOne
    @JoinColumn(name = "table_id")
    private DiningTable table;

    private String status;

    private int quantity;

    private String transactionId = UUID.randomUUID().toString();

    private String receipt;

}
