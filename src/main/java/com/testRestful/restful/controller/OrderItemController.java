// package com.testRestful.restful.controller;

// import com.testRestful.restful.entity.OrderItem;
// import com.testRestful.restful.service.OrderItemService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RestController;

// @RestController
// public class OrderItemController {

//     private final OrderItemService orderItemService;

//     @Autowired
//     public OrderItemController(OrderItemService orderItemService) {
//         this.orderItemService = orderItemService;
//     }

//     @PostMapping("/orderItems")
//     public ResponseEntity<?> addOrderItems(@RequestBody OrderItem[] orderItems) {
//         orderItemService.saveOrderItems(orderItems); // เรียกใช้เมธอด saveOrderItems
//         return new ResponseEntity<>("Order items added successfully", HttpStatus.CREATED);
//     }

// }

package com.testRestful.restful.controller;

import com.testRestful.restful.entity.OrderItem;
import com.testRestful.restful.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class OrderItemController {

    private final OrderItemService orderItemService;

    @Autowired
    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @PostMapping("/orderItems")
    public ResponseEntity<?> addOrderItems(@RequestBody OrderItem[] orderItems) {
        for (OrderItem orderItem : orderItems) {
            orderItem.setOrderDate(new Date());
        }
        orderItemService.saveOrderItems(orderItems); // เรียกใช้เมธอด saveOrderItems
        return new ResponseEntity<>("Order items added successfully", HttpStatus.CREATED);
    }

}
