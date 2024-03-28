// package com.testRestful.restful.controller;

// import java.io.IOException;
// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.ModelAttribute;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RequestPart;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.multipart.MultipartFile;

// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.testRestful.restful.entity.Order;
// import com.testRestful.restful.service.OrderService;




// @RestController
// public class OrderController {

//     @Autowired
//     private OrderService service;

//     @PostMapping("/addOrder")
//     public Order addOrder(@ModelAttribute Order order, @RequestParam("file") MultipartFile file) {
//         try {
//             order.setFilename(file.getOriginalFilename());
//             order.setData(file.getBytes());
//             return service.saveOrder(order, file);
//         } catch (IOException e) {
//             // การจัดการข้อผิดพลาดเมื่อเกิดข้อผิดพลาดในการอ่านข้อมูลจากไฟล์
//             return null; // หรือจัดการข้อผิดพลาดอื่น ๆ ตามที่คุณต้องการ
//         }
//     }
    

//     @PostMapping("/addOrders")
//     public List<Order> addOrders(@RequestBody List<Order> orders) {
//         return service.saveOrders(orders);
//     }

//     @GetMapping("/orders")
//     public List<Order> findAllOrders() {
//         return service.getOrders();
//     }

//     @GetMapping("/orderById/{id}")
//     public Order findIOrderById(@PathVariable int id) {
//         return service.getOrdersById(id);
//     }

//     @GetMapping("/orderByName/{name}")
//     public Order findIOrderByName(@PathVariable String name) {
//         return service.getOrdersByName(name);
//     }

//     // @PutMapping("/update")
//     // public Order updateOrder(@RequestBody Order order) {
//     //     return service.updateOrder(order);
//     // }

// // // OrderController.java
// // @PutMapping("/update/{id}")
// // public ResponseEntity<Order> updateOrder(@PathVariable("id") Integer id, @ModelAttribute Order order, @RequestParam("file") MultipartFile file) {
// //     try {
// //         order.setFilename(file.getOriginalFilename());
// //         order.setData(file.getBytes());
// //         Order updatedOrder = service.updateOrder(id, order, file); // แก้ไขตรงนี้
// //         return ResponseEntity.ok(updatedOrder);
// //     } catch (IOException e) {
// //         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
// //     }
// // }

// // OrderController.java
// @PutMapping("/update/{id}")
// public ResponseEntity<Order> updateOrder(@PathVariable("id") Integer id, @ModelAttribute Order order, @RequestParam("file") MultipartFile file) {
//     try {
//         if (file != null && !file.isEmpty()) {
//             order.setFilename(file.getOriginalFilename());
//             order.setData(file.getBytes());
//         }
//         Order updatedOrder = service.updateOrder(id, order, file); // แก้ไขตรงนี้
//         return ResponseEntity.ok(updatedOrder);
//     } catch (IOException e) {
//         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//     }
// }

    

//     @DeleteMapping("/delete/{id}")
//     public String deleteOrder(@PathVariable int id) {
//         return service.deleteOrder(id);
//     }

//     @PostMapping("/upload")
//     public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
//         try {
//             Order order = new Order();
//             order.setFilename(file.getOriginalFilename());
//             order.setData(file.getBytes());
//             service.saveOrder(order, file); // ส่ง MultipartFile ไปในเมธอด saveOrderด้วย
//             String message = "File uploaded successfully!";
//             HttpStatus httpStatus = HttpStatus.CREATED;
//             return new ResponseEntity<>(message, httpStatus);
//         } catch (IOException e) {
//             return ResponseEntity.status(500).build();
//         }
//     }

//     @GetMapping("/files")
//     public ResponseEntity<List<Order>> getFile() {
//         List<Order> files = service.getOrders();
//         return ResponseEntity.ok(files);
//     }

// }





package com.testRestful.restful.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.testRestful.restful.entity.Order;
import com.testRestful.restful.service.OrderService;



@RestController
public class OrderController {

    @Autowired
    private OrderService service;

    @PostMapping("/addOrder")
    public Order addOrder(@ModelAttribute Order order, @RequestParam("file") MultipartFile file) {
        try {
            order.setFilename(file.getOriginalFilename());
            order.setData(file.getBytes());
            return service.saveOrder(order, file);
        } catch (IOException e) {
            // การจัดการข้อผิดพลาดเมื่อเกิดข้อผิดพลาดในการอ่านข้อมูลจากไฟล์
            return null; // หรือจัดการข้อผิดพลาดอื่น ๆ ตามที่คุณต้องการ
        }
    }
    

    @PostMapping("/addOrders")
    public List<Order> addOrders(@RequestBody List<Order> orders) {
        return service.saveOrders(orders);
    }

    @GetMapping("/orders")
    public List<Order> findAllOrders() {
        return service.getOrders();
    }

    @GetMapping("/orderById/{id}")
    public Order findIOrderById(@PathVariable int id) {
        return service.getOrdersById(id);
    }

    @GetMapping("/orderByName/{name}")
    public Order findIOrderByName(@PathVariable String name) {
        return service.getOrdersByName(name);
    }

// OrderController.java
    @PutMapping("/update/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable("id") Integer id, @ModelAttribute Order order, @RequestParam("file") MultipartFile file) {
        try {
            if (file != null && !file.isEmpty()) {
                order.setFilename(file.getOriginalFilename());
                order.setData(file.getBytes());
            }
            Order updatedOrder = service.updateOrder(id, order, file); // แก้ไขตรงนี้
            return ResponseEntity.ok(updatedOrder);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    

    @DeleteMapping("/delete/{id}")
    public String deleteOrder(@PathVariable int id) {
        return service.deleteOrder(id);
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            Order order = new Order();
            order.setFilename(file.getOriginalFilename());
            order.setData(file.getBytes());
            service.saveOrder(order, file); // ส่ง MultipartFile ไปในเมธอด saveOrderด้วย
            String message = "File uploaded successfully!";
            HttpStatus httpStatus = HttpStatus.CREATED;
            return new ResponseEntity<>(message, httpStatus);
        } catch (IOException e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/files")
    public ResponseEntity<List<Order>> getFile() {
        List<Order> files = service.getOrders();
        return ResponseEntity.ok(files);
    }

}


