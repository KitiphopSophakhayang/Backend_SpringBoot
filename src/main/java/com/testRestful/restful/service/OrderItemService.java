// package com.testRestful.restful.service;

// import com.testRestful.restful.entity.OrderItem;
// import com.testRestful.restful.exceptions.OrderException;
// import com.testRestful.restful.models.DailyTotalPrice;
// import com.testRestful.restful.models.Top5MenuList;
// import com.testRestful.restful.repository.OrderItemRepository;

// import org.springframework.stereotype.Service;

// import java.math.BigDecimal;
// import java.math.RoundingMode;
// import java.text.SimpleDateFormat;
// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;
// import java.util.Optional;

// @Service
// public class OrderItemService {

//     private final OrderItemRepository orderItemRepository;

//     public OrderItemService(OrderItemRepository orderItemRepository) {
//         this.orderItemRepository = orderItemRepository;
//     }

//     public void saveOrderItems(OrderItem[] orderItems, String transactionId) {
//         for (OrderItem orderItem : orderItems) {
//             orderItem.setTransactionId(transactionId); // กำหนด transactionId ให้กับทุกรายการอาหาร
//             orderItemRepository.save(orderItem);
//         }
//     }

//     public void saveOrderItem(OrderItem orderItem) {
//         orderItemRepository.save(orderItem);
//     }

//     public List<OrderItem> getAllOrderItems() {
//         return orderItemRepository.findAll();
//     }

//     public List<OrderItem> getOrderItemsByTransactionId(String transactionId) {
//         return orderItemRepository.findByTransactionId(transactionId);
//     }

//     public List<OrderItem> getTableIdAndStatus(Long tableId) {
//         return orderItemRepository.getTableIdAndStatus(tableId);
//     }

//     public List<DailyTotalPrice> getTotalPriceByWeekAndGetDayName() {
//         List<Object[]> result = orderItemRepository.getTotalPriceByWeekAndGetDayName();

//         List<DailyTotalPrice> dailyTotalPrices = new ArrayList<>();
//         for (Object[] row : result) {
//             String dayOfWeek = (String) row[0];
//             double totalPrice = (double) row[1];
//             dailyTotalPrices.add(new DailyTotalPrice(dayOfWeek, totalPrice));
//         }

//         return dailyTotalPrices;
//     }

//     public Integer getAllTotalPrice() {
//         return orderItemRepository.getAllTotalPrice();
//     }

//     public Integer getAllOrder() {
//         return orderItemRepository.getAllOrder();
//     }

//     public Map<String, String> getTotalPriceByDateInOneWeek() {
//         List<Object[]> resultQuery = orderItemRepository.getTotalPriceByDateInOneWeek();
//         Map<String, String> afterFormat = new HashMap<>();

//         for (Object[] dateTime : resultQuery) {
//             SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//             String formatDate = simpleDateFormat.format(dateTime[0]);
//             BigDecimal price = (BigDecimal) dateTime[1];
//             // Set scale to 2 for two decimal places
//             BigDecimal scaledPrice = price.setScale(2, RoundingMode.HALF_UP);
//             afterFormat.put(formatDate, scaledPrice.toPlainString());
//         }

//         return afterFormat;
//     }

//     public List<Top5MenuList> getTop5MenuList() {
//         List<Object[]> result = orderItemRepository.getTop5MenuList();
//         List<Top5MenuList> top5MenuList = new ArrayList<>();

//         for (Object[] row : result) {
//             String menuName = (String) row[0];
//             BigDecimal totalOrderedQuantity = (BigDecimal) row[1];
//             Integer newVal = totalOrderedQuantity.intValue();

//             Top5MenuList menu = new Top5MenuList(menuName, newVal);
//             top5MenuList.add(menu);
//         }

//         return top5MenuList;
//     }

//     public List<OrderItem> getOrderStatus(String status) {
//         if (status.equals("pending")) {
//             return orderItemRepository.getOrderPending();
//         } else {
//             return orderItemRepository.getOrderSuccess();
//         }
//     }

//     public Boolean updateOrderStatus(String status, Long orderItemId) {
//         Optional<OrderItem> orderResult = orderItemRepository.findById(orderItemId);

//         if (orderResult.isPresent()) {
//             OrderItem existingOrderResult = orderResult.get();
//             existingOrderResult.setStatus(status);
//             orderItemRepository.save(existingOrderResult);
//         } else {
//             throw new OrderException(orderItemId);
//         }
//         return true;
//     }
// }

package com.testRestful.restful.service;

import com.testRestful.restful.entity.OrderItem;
import com.testRestful.restful.exceptions.OrderException;
import com.testRestful.restful.models.DailyTotalPrice;
import com.testRestful.restful.models.Top5MenuList;
import com.testRestful.restful.repository.OrderItemRepository;
import com.testRestful.restful.repository.OrderRepository; // เพิ่ม OrderRepository

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class OrderItemService {

    @Autowired
    private OrderRepository repository; // แก้ไขการอ้างอิง

    @Autowired
    private OrderItemRepository orderItemRepository; // เพิ่ม OrderItemRepository

    // public List<Object[]> getGroupedOrderItems() {
    // return orderItemRepository.getGroupedOrderItems();
    // }

    public List<Map<String, Object>> getGroupedOrderItems() {
        List<Object[]> groupedOrderItems = orderItemRepository.getGroupedOrderItems();
        List<Map<String, Object>> formattedOrderItems = new ArrayList<>();

        for (Object[] item : groupedOrderItems) {
            Map<String, Object> formattedItem = new HashMap<>();
            formattedItem.put("transaction_id", item[0]);

            // Convert Unix timestamp to LocalDateTime
            Timestamp timestamp = (Timestamp) item[1];
            LocalDateTime orderDate = timestamp.toLocalDateTime();
            formattedItem.put("order_date", orderDate);

            formattedItem.put("menu_names", item[2]);
            formattedItem.put("table_id", item[3]);
            formattedItem.put("quantities", item[4]);
            formattedItem.put("statuses", item[5]);
            formattedItem.put("total_price", item[6]);
            formattedOrderItems.add(formattedItem);
        }

        return formattedOrderItems;
    }


    public List<Object[]> getCompleteGroupedOrderItems() {
        return orderItemRepository.getCompleteGroupedOrderItems();
    }
    
    


    public List<Object[]> getOrderItemsFormatted(Long tableId, String status) {
        return orderItemRepository.getOrderItemsFormatted(tableId, status);
    }

    // // สร้างเมธอด getOrderItemsFormatted() และเชื่อมต่อกับ OrderItemRepository
    // public List<Object[]> getOrderItemsFormatted() {
    // return orderItemRepository.getOrderItemsFormatted();
    // }

    public void saveOrderItems(OrderItem[] orderItems, String transactionId) {
        for (OrderItem orderItem : orderItems) {
            orderItem.setTransactionId(transactionId); // กำหนด transactionId ให้กับทุกรายการอาหาร
            orderItemRepository.save(orderItem);
        }
    }

    public void saveOrderItem(OrderItem orderItem) {
        orderItemRepository.save(orderItem);
    }

    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }

    public List<OrderItem> getOrderItemsByTransactionId(String transactionId) {
        return orderItemRepository.findByTransactionId(transactionId);
    }

    public List<OrderItem> getTableIdAndStatus(Long tableId) {
        return orderItemRepository.getTableIdAndStatus(tableId);
    }

    public List<DailyTotalPrice> getTotalPriceByWeekAndGetDayName() {
        List<Object[]> result = orderItemRepository.getTotalPriceByWeekAndGetDayName();

        List<DailyTotalPrice> dailyTotalPrices = new ArrayList<>();
        for (Object[] row : result) {
            String dayOfWeek = (String) row[0];
            double totalPrice = (double) row[1];
            dailyTotalPrices.add(new DailyTotalPrice(dayOfWeek, totalPrice));
        }

        return dailyTotalPrices;
    }

    public Integer getAllTotalPrice() {
        return orderItemRepository.getAllTotalPrice();
    }

    public Integer getAllOrder() {
        return orderItemRepository.getAllOrder();
    }

    public Map<String, String> getTotalPriceByDateInOneWeek() {
        List<Object[]> resultQuery = orderItemRepository.getTotalPriceByDateInOneWeek();
        Map<String, String> afterFormat = new HashMap<>();

        for (Object[] dateTime : resultQuery) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formatDate = simpleDateFormat.format(dateTime[0]);
            BigDecimal price = (BigDecimal) dateTime[1];
            // Set scale to 2 for two decimal places
            BigDecimal scaledPrice = price.setScale(2, RoundingMode.HALF_UP);
            afterFormat.put(formatDate, scaledPrice.toPlainString());
        }

        return afterFormat;
    }

    public List<Top5MenuList> getTop5MenuList() {
        List<Object[]> result = orderItemRepository.getTop5MenuList();
        List<Top5MenuList> top5MenuList = new ArrayList<>();

        for (Object[] row : result) {
            String menuName = (String) row[0];
            BigDecimal totalOrderedQuantity = (BigDecimal) row[1];
            Integer newVal = totalOrderedQuantity.intValue();

            Top5MenuList menu = new Top5MenuList(menuName, newVal);
            top5MenuList.add(menu);
        }

        return top5MenuList;
    }

    public List<OrderItem> getOrderStatus(String status) {
        if (status.equals("pending")) {
            return orderItemRepository.getOrderPending();
        } else {
            return orderItemRepository.getOrderSuccess();
        }
    }

    public Boolean updateOrderStatus(String status, Long orderItemId) {
        Optional<OrderItem> orderResult = orderItemRepository.findById(orderItemId);

        if (orderResult.isPresent()) {
            OrderItem existingOrderResult = orderResult.get();
            existingOrderResult.setStatus(status);
            orderItemRepository.save(existingOrderResult);
        } else {
            throw new OrderException(orderItemId);
        }
        return true;
    }

    public List<OrderItem> updateOrderPaymentStatus(List<Long> orderItemIds, String status) {
        List<OrderItem> orderItemResultList = orderItemRepository.findAllById(orderItemIds);
        for (OrderItem orderItem : orderItemResultList) {
            orderItem.setPayment_status(status);
        }
        orderItemRepository.saveAll(orderItemResultList);
        return orderItemResultList;
    }
}
