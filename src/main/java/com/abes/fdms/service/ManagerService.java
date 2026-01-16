package com.abes.fdms.service;

import java.util.Map;

import com.abes.fdms.dao.FoodItemDao;
import com.abes.fdms.dao.FoodItemDaoImpl;
import com.abes.fdms.dao.UserDao;
import com.abes.fdms.dao.UserDaoImpl;
import com.abes.fdms.dto.FoodItemDto;
import com.abes.fdms.dto.ManagerDto;

public class ManagerService {
//    private final FoodItemDao foodItemDao = new FoodItemDaoImpl();
    private final UserDao userDao = new UserDaoImpl();

//    public void addFoodItem(String name, double price, int quantity) {
//        FoodItemDto item = new FoodItemDto(name, price);
//        foodItemDao.addFoodItem(item, quantity);
//    }

//    public void restockFoodItem(String name, int quantity) {
//        FoodItemDto item = foodItemDao.getFoodItemByName(name);
//        if (item != null) {
//            int current = foodItemDao.getInventory().get(item);
//            foodItemDao.getInventory().put(item, current + quantity);
//        }
//    }
// 
//    public void removeFoodItem(String name) {
//        foodItemDao.removeFoodItem(name);
//    }

    public ManagerDto loginManager(String id, String password) {
        ManagerDto manager = userDao.getManagerById(id);
        if (manager != null && manager.getPassword().equals(password)) {
            return manager;
        }
        return null;
    }  
} 