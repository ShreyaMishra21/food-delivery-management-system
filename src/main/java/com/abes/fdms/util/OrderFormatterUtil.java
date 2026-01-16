package com.abes.fdms.util;

import com.abes.fdms.dto.OrderDto;
import com.abes.fdms.dto.FoodItemDto;
import java.util.Map;


//Utility class for formatting order details into a readable string.

public class OrderFormatterUtil {

    //Returns a formatted string with all details of the given order.
    public static String formatOrderDetails(OrderDto order) {
        StringBuilder sb = new StringBuilder();

        // Add customer name
        sb.append("Order for ").append(order.getCustomer().getName()).append("\n");

        // List each food item and its quantity
        for (Map.Entry<FoodItemDto, Integer> entry : order.getItemsOrdered().entrySet()) {
            sb.append(entry.getKey()) // FoodItemDto's toString() gives name and price
              .append(" x ")
              .append(entry.getValue())
              .append("\n");
        }

        // Add total cost
        double totalCost = OrderCostUtil.calculateTotalCost(order);
        sb.append("Total Cost: Rs ").append(String.format("%.2f", totalCost)).append("\n");

        // Add order status
        sb.append("Status: ").append(order.getStatus());

        return sb.toString();
    }
}
