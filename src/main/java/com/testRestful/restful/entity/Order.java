package com.testRestful.restful.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Order_menu")

public class Order {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String tpye_menu;
    private double price;
    private String image;
}
