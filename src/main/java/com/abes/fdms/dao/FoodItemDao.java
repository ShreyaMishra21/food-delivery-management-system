package com.abes.fdms.dao;

import com.abes.fdms.dto.FoodItemDto;
import java.util.Map;

public interface FoodItemDao {
    Map<FoodItemDto, Integer> getInventory();
    void addFoodItem(FoodItemDto item, int quantity);
    void removeFoodItem(String name);
    FoodItemDto getFoodItemByName(String name);
}
