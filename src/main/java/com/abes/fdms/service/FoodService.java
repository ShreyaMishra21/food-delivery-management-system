package com.abes.fdms.service;

import com.abes.fdms.dao.FoodItemDao;
import com.abes.fdms.dao.FoodItemDaoImpl;
import com.abes.fdms.dto.FoodItemDto;
import com.abes.fdms.exception.DuplicateItemException;

import java.util.Map;

public class FoodService {
    private final FoodItemDao foodItemDao = new FoodItemDaoImpl();

    public void addNewItem(String name, double price, int quantity) throws DuplicateItemException {
        FoodItemDto existing = foodItemDao.getFoodItemByName(name);
        if (existing != null) {
            throw new DuplicateItemException(name);
        }
        FoodItemDto item = new FoodItemDto(name, price);
        foodItemDao.addFoodItem(item, quantity);
    }


    public void restockItem(String name, int quantity) {
        FoodItemDto item = foodItemDao.getFoodItemByName(name);
        if (item != null) {
            int current = foodItemDao.getInventory().get(item);
            foodItemDao.getInventory().put(item, current + quantity);
        }
    }

    public void removeItem(String name) {
        foodItemDao.removeFoodItem(name);
    }

    public Map<FoodItemDto, Integer> getMenu() {
        return foodItemDao.getInventory();
    }

    public FoodItemDto getFoodItemByName(String name) {
        return foodItemDao.getFoodItemByName(name);
    }
}
