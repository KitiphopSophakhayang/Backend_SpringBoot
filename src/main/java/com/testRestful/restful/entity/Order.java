package com.testRestful.restful.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String foodType;
    private Double price;


    @Column(name = "file_name")
    private String filename;

    @Lob
    @Column(name = "file", length = 1048576)
    private byte[] data;

}

// package com.testRestful.restful.entity;

// import jakarta.persistence.CascadeType;
// import jakarta.persistence.Column;
// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.Lob;
// import jakarta.persistence.OneToOne;
// import jakarta.persistence.Table;
// import lombok.AllArgsConstructor;
// import lombok.Data;
// import lombok.NoArgsConstructor;

// @Data
// @AllArgsConstructor
// @NoArgsConstructor
// @Entity
// @Table(name = "Order_menu")

// public class Order {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Integer id;

//     private String name;
//     private String foodType;
//     private Double price;


//     // @Column(name = "file_name")
//     // private String filename;

//     // @Lob
//     // @Column(name = "file", length = 1048576)
//     // private byte[] data;

//     @OneToOne(cascade = CascadeType.ALL)
//     @JoinColumn(name = "file_id")
//     private FileEntity fileEntity;

// }