package com.testRestful.restful.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Lob;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id") 
    private Integer id;
    
    private String name;

    @ManyToOne
    @JoinColumn(name = "food_type_id")
    private FoodType foodTypes;
    
    @Column(name = "food_type")
    private String foodType;

    private Double price;

    @Column(name = "file_name")
    private String filename;

    @Lob
    @Column(name = "file", length = 8388608)
    private byte[] data;
}
