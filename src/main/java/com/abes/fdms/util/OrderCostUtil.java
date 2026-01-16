package com.abes.fdms.util;

import com.abes.fdms.dto.OrderDto;
import com.abes.fdms.dto.FoodItemDto;
import java.util.Map;

//Utility class to calculate the total cost of an order

public class OrderCostUtil {

    //The total cost as a double
    public static double calculateTotalCost(OrderDto order) {
        double totalCost = 0.0;

        // Loop through each food item and its quantity in the order
        for (Map.Entry<FoodItemDto, Integer> itemEntry : order.getItemsOrdered().entrySet()) {
            FoodItemDto foodItem = itemEntry.getKey();
            int quantity = itemEntry.getValue();
            double itemPrice = foodItem.getPrice();

            // Add the cost for this item (price * quantity) to the total
            totalCost += itemPrice * quantity;
        }

        return totalCost;
    }
}
