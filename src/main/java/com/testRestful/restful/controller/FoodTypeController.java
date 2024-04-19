package com.testRestful.restful.controller;

import com.testRestful.restful.entity.FoodType;
import com.testRestful.restful.service.FoodTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FoodTypeController {

    @Autowired
    private FoodTypeService foodTypeService;

    @PostMapping("/food-types")
    public FoodType addFoodType(@RequestBody FoodType foodType) {
        return foodTypeService.saveFoodType(foodType);
    }

    @GetMapping("/food-types")
    public List<FoodType> getAllFoodTypes() {
        return foodTypeService.getAllFoodTypes();
    }

    @GetMapping("/food-types/{id}")
    public FoodType getFoodTypeById(@PathVariable int id) {
        return foodTypeService.getFoodTypeById(id);
    }

    @GetMapping("/food-types/name/{name}")
    public FoodType getFoodTypeByName(@PathVariable String name) {
        return foodTypeService.getFoodTypeByName(name);
    }

    @PutMapping("/food-types/{id}")
    public FoodType updateFoodType(@PathVariable int id, @RequestBody FoodType updatedFoodType) {
        return foodTypeService.updateFoodType(id, updatedFoodType);
    }


    @DeleteMapping("/food-types/{id}")
    public String deleteFoodType(@PathVariable int id) {
        return foodTypeService.deleteFoodType(id);
    }
}
