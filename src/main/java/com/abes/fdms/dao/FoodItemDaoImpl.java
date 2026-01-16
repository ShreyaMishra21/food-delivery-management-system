package com.abes.fdms.dao;

import com.abes.fdms.dto.FoodItemDto;
import com.abes.fdms.util.CollectionUtil;
import java.util.Map;

public class FoodItemDaoImpl implements FoodItemDao {
    @Override
    public Map<FoodItemDto, Integer> getInventory() {
        return CollectionUtil.inventory;
    }

    @Override
    public void addFoodItem(FoodItemDto item, int quantity) {
        CollectionUtil.inventory.put(item, quantity);
    }

    @Override
    public void removeFoodItem(String name) {
        CollectionUtil.inventory.keySet().removeIf(i -> i.getName().equalsIgnoreCase(name));
    }

    @Override
    public FoodItemDto getFoodItemByName(String name) {
        return CollectionUtil.inventory.keySet().stream()
            .filter(item -> item.getName().equalsIgnoreCase(name))
            .findFirst()
            .orElse(null);
    }
}
