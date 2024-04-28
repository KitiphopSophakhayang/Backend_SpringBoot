// package com.testRestful.restful.controller;

// import com.testRestful.restful.entity.OrderItem;
// import com.testRestful.restful.models.Top5MenuList;
// import com.testRestful.restful.models.UpdOrderStatusBean;
// import com.testRestful.restful.service.OrderItemService;
// import org.springframework.beans.factory.annotation.Autowired;

// import org.springframework.data.repository.query.Param;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.ResponseStatus;
// import org.springframework.web.bind.annotation.RestController;

// import java.util.Date;
// import java.util.List;
// import java.util.Map;
// import java.util.UUID;
// import org.springframework.web.bind.annotation.PutMapping;

// @RestController
// public class OrderItemController {

//     private final OrderItemService orderItemService;

//     @Autowired
//     public OrderItemController(OrderItemService orderItemService) {
//         this.orderItemService = orderItemService;
//     }

//     @PostMapping("/orderItems")
//     public ResponseEntity<?> addOrderItems(@RequestBody OrderItem[] orderItems) {
//         String transactionId = UUID.randomUUID().toString(); // สร้าง transactionId
//         for (OrderItem orderItem : orderItems) {
//             orderItem.setOrderDate(new Date());
//             orderItem.setTransactionId(transactionId); // กำหนด transactionId ให้กับ OrderItem ทุกรายการ
//         }
//         orderItemService.saveOrderItems(orderItems, transactionId); // เรียกใช้เมธอด saveOrderItems พร้อมส่ง
//                                                                     // transactionId มาด้วย
//         return new ResponseEntity<>("Order items added successfully", HttpStatus.CREATED);
//     }

//     @GetMapping("/orderItems")
//     public ResponseEntity<List<OrderItem>> getOrderItems() {
//         List<OrderItem> orderItems = orderItemService.getAllOrderItems();
//         return new ResponseEntity<>(orderItems, HttpStatus.OK);
//     }

//     @GetMapping("/orderItems/{transactionId}")
//     public ResponseEntity<List<OrderItem>> getOrderItemsByTransactionId(@PathVariable String transactionId) {
//         List<OrderItem> orderItems = orderItemService.getOrderItemsByTransactionId(transactionId);
//         return new ResponseEntity<>(orderItems, HttpStatus.OK);
//     }

//     @GetMapping("/orderItems/getTableData/{id}")
//     public List<OrderItem> getTableIdAndStatus(@PathVariable("id") Long tableId) {
//         return orderItemService.getTableIdAndStatus(tableId);
//     }

//     // @GetMapping("/orderItems/getTotalPriceByWeekAndGetDayName")
//     // public List<DailyTotalPrice> getTotalPriceByWeek() {
//     // return orderItemService.getTotalPriceByWeekAndGetDayName();
//     // }

//     @GetMapping("/orderItems/getAllTotalPrice")
//     public Object getAllTotalPrice() {
//         return orderItemService.getAllTotalPrice();
//     }

//     @GetMapping("/orderItems/getAllOrder")
//     public Object getAllOrder() {
//         return orderItemService.getAllOrder();
//     }

//     @GetMapping("/orderItems/getTotalPriceByDateInOneWeek")
//     public Map<String, String> getTotalPriceByDateInOneWeek() {
//         return orderItemService.getTotalPriceByDateInOneWeek();
//     }

//     @GetMapping("/orderItems/getTop5MenuList")
//     public List<Top5MenuList> getTop5MenuList() {
//         return orderItemService.getTop5MenuList();
//     }

//     @GetMapping("/orderItems/getOrderStatus")
//     public List<OrderItem> getOrderStatus(@Param("status") String status) {
//         return orderItemService.getOrderStatus(status);
//     }

//     @PutMapping("/orderItems/updateOrderStatus")
//     @ResponseStatus(HttpStatus.CREATED)
//     public Boolean updateOrderStatus(@RequestBody UpdOrderStatusBean updOrderStatusBean) {
//         return orderItemService.updateOrderStatus(updOrderStatusBean.getStatus(), updOrderStatusBean.getOrderItemId());
//     }
// }

package com.testRestful.restful.controller;

import com.testRestful.restful.entity.OrderItem;
import com.testRestful.restful.models.Top5MenuList;
import com.testRestful.restful.models.UpdOrderItemPayment;
import com.testRestful.restful.models.UpdOrderStatusBean;
import com.testRestful.restful.service.OrderItemService;
import com.testRestful.restful.repository.OrderItemRepository; // Import OrderItemRepository
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class OrderItemController {

    private final OrderItemService orderItemService;
    private final OrderItemRepository orderItemRepository; // เพิ่มตัวแปร orderItemRepository ที่นี่

    @Autowired
    public OrderItemController(OrderItemService orderItemService, OrderItemRepository orderItemRepository) {
        this.orderItemService = orderItemService;
        this.orderItemRepository = orderItemRepository; // กำหนดค่าให้กับ orderItemRepository ที่นี่
    }

    @GetMapping("/orderItems/getTop5MenuList")
    public List<Top5MenuList> getTop5MenuList() {
        return orderItemService.getTop5MenuList();
    }

    // @GetMapping("/orderItems/getFormatted")
    // public ResponseEntity<List<Object[]>> getOrderItemsFormatted() {
    // List<Object[]> formattedOrderItems =
    // orderItemRepository.getOrderItemsFormatted();
    // return new ResponseEntity<>(formattedOrderItems, HttpStatus.OK);
    // }

    // // เพิ่มเมธอดเพื่อเรียกใช้ getOrderItemsFormatted() ที่อยู่ใน
    // OrderItemRepository
    // @GetMapping("/formatted")
    // public ResponseEntity<List<Object[]>> getOrderItemsFormatted() {
    // List<Object[]> formattedOrderItems =
    // orderItemRepository.getOrderItemsFormatted();
    // return new ResponseEntity<>(formattedOrderItems, HttpStatus.OK);
    // }

    @PostMapping("/orderItems")
    public ResponseEntity<?> addOrderItems(@RequestBody OrderItem[] orderItems) {
        String transactionId = UUID.randomUUID().toString(); // สร้าง transactionId
        for (OrderItem orderItem : orderItems) {
            orderItem.setOrderDate(new Date());
            orderItem.setTransactionId(transactionId); // กำหนด transactionId ให้กับ OrderItem ทุกรายการ
        }
        orderItemService.saveOrderItems(orderItems, transactionId); // เรียกใช้เมธอด saveOrderItems พร้อมส่ง
                                                                    // transactionId มาด้วย
        return new ResponseEntity<>("Order items added successfully", HttpStatus.CREATED);
    }

    @GetMapping("/orderItems")
    public ResponseEntity<List<OrderItem>> getOrderItems() {
        List<OrderItem> orderItems = orderItemService.getAllOrderItems();
        return new ResponseEntity<>(orderItems, HttpStatus.OK);
    }

    @GetMapping("/orderItems/{transactionId}")
    public ResponseEntity<List<OrderItem>> getOrderItemsByTransactionId(@PathVariable String transactionId) {
        List<OrderItem> orderItems = orderItemService.getOrderItemsByTransactionId(transactionId);
        return new ResponseEntity<>(orderItems, HttpStatus.OK);
    }

    @GetMapping("/orderItems/getTableData/{id}")
    public List<OrderItem> getTableIdAndStatus(@PathVariable("id") Long tableId) {
        return orderItemService.getTableIdAndStatus(tableId);
    }

    // @GetMapping("/orderItems/getTotalPriceByWeekAndGetDayName")
    // public List<DailyTotalPrice> getTotalPriceByWeek() {
    // return orderItemService.getTotalPriceByWeekAndGetDayName();
    // }

    @GetMapping("/orderItems/getAllTotalPrice")
    public Object getAllTotalPrice() {
        return orderItemService.getAllTotalPrice();
    }

    @GetMapping("/orderItems/getAllOrder")
    public Object getAllOrder() {
        return orderItemService.getAllOrder();
    }

    @GetMapping("/orderItems/getTotalPriceByDateInOneWeek")
    public Map<String, String> getTotalPriceByDateInOneWeek() {
        return orderItemService.getTotalPriceByDateInOneWeek();
    }

    // @GetMapping("/orderItems/getTop5MenuList")
    // public List<Top5MenuList> getTop5MenuList() {
    // return orderItemService.getTop5MenuList();
    // }

    @GetMapping("/orderItems/getOrderStatus")
    public List<OrderItem> getOrderStatus(@Param("status") String status) {
        return orderItemService.getOrderStatus(status);
    }

    @PutMapping("/orderItems/updateOrderStatus")
    @ResponseStatus(HttpStatus.CREATED)
    public Boolean updateOrderStatus(@RequestBody UpdOrderStatusBean updOrderStatusBean) {
        return orderItemService.updateOrderStatus(updOrderStatusBean.getStatus(), updOrderStatusBean.getOrderItemId());
    }

    // @GetMapping("/orderItems/getFormatted")
    // public ResponseEntity<List<Object[]>> getOrderItemsFormatted() {
    // List<Object[]> formattedOrderItems =
    // orderItemService.getOrderItemsFormatted(); // เรียกใช้งานผ่าน
    // OrderItemService
    // return new ResponseEntity<>(formattedOrderItems, HttpStatus.OK);
    // }

    // @GetMapping("/orderItems")
    // public ResponseEntity<List<OrderItem>> getOrderItems() {
    // List<OrderItem> orderItems = orderItemService.getAllOrderItems();
    // return new ResponseEntity<>(orderItems, HttpStatus.OK);
    // }

    // @GetMapping("/orderItems/getFormatted")
    // public Object getOrderItemsFormatted() {
    // return orderItemService.getOrderItemsFormatted();
    // }

    @GetMapping("/orderItems/table/{tableId}/status/{status}")
    public ResponseEntity<List<Object[]>> getOrderItemsByTableIdAndStatus(@PathVariable Long tableId,
            @PathVariable String status) {
        List<Object[]> orderItems = orderItemService.getOrderItemsFormatted(tableId, status);
        return new ResponseEntity<>(orderItems, HttpStatus.OK);
    }

    // @GetMapping("/orderItems/groupedData")
    // public ResponseEntity<?> getGroupedOrderItems() {
    // List<Object[]> groupedOrderItems = orderItemService.getGroupedOrderItems();
    // return new ResponseEntity<>(groupedOrderItems, HttpStatus.OK);
    // }

    @GetMapping("/orderItems/groupedData")
    public ResponseEntity<?> getGroupedOrderItems() {
        List<Map<String, Object>> formattedOrderItems = orderItemService.getGroupedOrderItems();
        return new ResponseEntity<>(formattedOrderItems, HttpStatus.OK);
    }

    @PutMapping("/orderItems/updateOrderPaymentStatus")
    @ResponseStatus(HttpStatus.CREATED)
    public List<OrderItem> updateOrderPaymentStatus(@RequestBody UpdOrderItemPayment updOrderItemPayment) {
        return orderItemService.updateOrderPaymentStatus(updOrderItemPayment.getOrderItemIds(), updOrderItemPayment.getStatus());
    }

}
