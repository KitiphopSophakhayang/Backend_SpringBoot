// package com.testRestful.restful.service;
// import java.io.IOException;
// import java.util.List;
// import java.util.Optional;

// import org.springframework.web.multipart.MultipartFile;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import com.testRestful.restful.entity.Order;
// import com.testRestful.restful.repository.OrderRepository;

// @Service
// public class OrderService {

//     @Autowired
//     private OrderRepository repository;

//     public Order saveOrder(Order order, MultipartFile file) {
//         return repository.save(order);
//     }

//     //     public Order saveOrder(Order order){
//     //     return repository.save(order);
//     // }

//     public List<Order> saveOrders(List<Order> orders){
//         return repository.saveAll(orders);
//     }

//     public List<Order> getOrders(){
//         return repository.findAll();
//     }

//     public Order getOrdersById(int id){
//         Optional<Order> optionalOrder = repository.findById(id);
//         return optionalOrder.orElse(null);
//     }

//     public Order getOrdersByName(String name){
//         return repository.findByName(name);
//     }

//     public String deleteOrder(int id){
//         repository.deleteById(id);
//         return "Order removed: " + id;
//     }

//     // public Order updateOrder(Integer id, Order updatedOrderData) {
//     //     Optional<Order> optionalExistingOrder = repository.findById(id);
//     //     if (optionalExistingOrder.isPresent()) {
//     //         Order existingOrder = optionalExistingOrder.get();
//     //         existingOrder.setName(updatedOrderData.getName());
//     //         existingOrder.setFoodType(updatedOrderData.getFoodType());
//     //         existingOrder.setPrice(updatedOrderData.getPrice());
//     //         existingOrder.setData(updatedOrderData.getData());
//     //         existingOrder.setFilename(updatedOrderData.getFilename());
//     //         return repository.save(existingOrder);
//     //     } else {
//     //         throw new IllegalArgumentException("Order with ID " + id + " not found.");
//     //     }
//     // }

// // // OrderService.java
// // public Order updateOrder(Integer id, Order updatedOrderData, MultipartFile file) {
// //     Optional<Order> optionalExistingOrder = repository.findById(id);
// //     if (optionalExistingOrder.isPresent()) {
// //         Order existingOrder = optionalExistingOrder.get();
// //         existingOrder.setName(updatedOrderData.getName());
// //         existingOrder.setFoodType(updatedOrderData.getFoodType());
// //         existingOrder.setPrice(updatedOrderData.getPrice());
// //         existingOrder.setData(updatedOrderData.getData());
// //         existingOrder.setFilename(updatedOrderData.getFilename());

// //         if (file != null && !file.isEmpty()) {
// //             try {
// //                 existingOrder.setFilename(file.getOriginalFilename());
// //                 existingOrder.setData(file.getBytes());
// //             } catch (IOException e) {
// //                 // Handle file IO exception
// //             }
// //         }

// //         return repository.save(existingOrder);
// //     } else {
// //         throw new IllegalArgumentException("Order with ID " + id + " not found.");
// //     }
// // }

// // OrderService.java
// public Order updateOrder(Integer id, Order updatedOrderData, MultipartFile file) {
//     Optional<Order> optionalExistingOrder = repository.findById(id);
//     if (optionalExistingOrder.isPresent()) {
//         Order existingOrder = optionalExistingOrder.get();
//         existingOrder.setName(updatedOrderData.getName());
//         existingOrder.setFoodType(updatedOrderData.getFoodType());
//         existingOrder.setPrice(updatedOrderData.getPrice());
//         existingOrder.setData(updatedOrderData.getData());
//         existingOrder.setFilename(updatedOrderData.getFilename());

//         if (file != null && !file.isEmpty()) {
//             try {
//                 existingOrder.setFilename(file.getOriginalFilename());
//                 existingOrder.setData(file.getBytes());
//             } catch (IOException e) {
//                 // Handle file IO exception
//             }
//         }

//         return repository.save(existingOrder);
//     } else {
//         throw new IllegalArgumentException("Order with ID " + id + " not found.");
//     }
// }   

// }

package com.testRestful.restful.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.testRestful.restful.entity.Order;
import com.testRestful.restful.repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    public Order saveOrder(Order order, MultipartFile file) {
        return repository.save(order);
    }

    public List<Order> saveOrders(List<Order> orders) {
        return repository.saveAll(orders);
    }

    public List<Order> getOrders() {
        return repository.findAll();
    }

    public Order getOrdersById(int id) {
        Optional<Order> optionalOrder = repository.findById(id);
        return optionalOrder.orElse(null);
    }

    public Order getOrdersByName(String name) {
        return repository.findByName(name);
    }

    public String deleteOrder(int id) {
        repository.deleteById(id);
        return "Order removed: " + id;
    }

    public Order updateOrder(Integer id, Order updatedOrderData, MultipartFile file) {
        Optional<Order> optionalExistingOrder = repository.findById(id);
        if (optionalExistingOrder.isPresent()) {
            Order existingOrder = optionalExistingOrder.get();
            existingOrder.setName(updatedOrderData.getName());
            existingOrder.setFoodType(updatedOrderData.getFoodType());
            existingOrder.setPrice(updatedOrderData.getPrice());
            existingOrder.setData(updatedOrderData.getData());
            existingOrder.setFilename(updatedOrderData.getFilename());

            if (file != null && !file.isEmpty()) {
                try {
                    existingOrder.setFilename(file.getOriginalFilename());
                    existingOrder.setData(file.getBytes());
                } catch (IOException e) {
                    // Handle file IO exception
                }
            }

            return repository.save(existingOrder);
        } else {
            throw new IllegalArgumentException("Order with ID " + id + " not found.");
        }
    }

    public Integer getAllMenu() {
        return repository.getAllMenu();
    }

}
