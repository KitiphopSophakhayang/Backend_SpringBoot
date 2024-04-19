package com.testRestful.restful.repository;

import com.testRestful.restful.entity.FoodType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodTypeRepository extends JpaRepository<FoodType, Integer> {
    FoodType findByName(String name);
}
