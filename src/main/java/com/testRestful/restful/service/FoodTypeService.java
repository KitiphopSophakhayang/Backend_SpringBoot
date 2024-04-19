package com.testRestful.restful.service;

import com.testRestful.restful.entity.FoodType;
import com.testRestful.restful.repository.FoodTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodTypeService {

    @Autowired
    private FoodTypeRepository repository;

    public FoodType saveFoodType(FoodType foodType) {
        return repository.save(foodType);
    }

    public List<FoodType> getAllFoodTypes() {
        return repository.findAll();
    }

    public FoodType getFoodTypeById(int id) {
        Optional<FoodType> optionalFoodType = repository.findById(id);
        return optionalFoodType.orElse(null);
    }

    public FoodType getFoodTypeByName(String name) {
        return repository.findByName(name);
    }

    public String deleteFoodType(int id) {
        repository.deleteById(id);
        return "Food Type removed: " + id;
    }

    public FoodType updateFoodType(int id, FoodType foodType) {
        // Check if the food type with the given ID exists
        Optional<FoodType> optionalFoodType = repository.findById(id);
        if (optionalFoodType.isPresent()) {
            FoodType existingFoodType = optionalFoodType.get();
            // Update the fields of the existing food type with the new values
            existingFoodType.setName(foodType.getName());
            // Add more fields if needed
    
            // Save the updated food type
            return repository.save(existingFoodType);
        } else {
            // If the food type with the given ID does not exist, return null or throw an exception
            return null;
        }
    }
    
}
