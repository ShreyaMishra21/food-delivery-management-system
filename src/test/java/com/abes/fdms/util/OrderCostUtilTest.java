package com.abes.fdms.util;

import com.abes.fdms.dto.*;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class OrderCostUtilTest {

    @Test
    void testCalculateTotalCost() {
        CustomerDto customer = new CustomerDto("c1", "Alice", "alice@email.com", "1234567890", "Pass@123");
        DeliveryPersonDto dp = new DeliveryPersonDto("dp1", "DP", "dp@email.com", "9999999999");
        Map<FoodItemDto, Integer> items = new HashMap<>();
        items.put(new FoodItemDto("Pizza", 100.0), 2);
        items.put(new FoodItemDto("Burger", 50.0), 3);
        OrderDto order = new OrderDto(customer, dp, items);

        double total = OrderCostUtil.calculateTotalCost(order);
        assertEquals(100.0 * 2 + 50.0 * 3, total, 0.001);
    }

    @Test
    void testCalculateTotalCost_EmptyOrder() {
        CustomerDto customer = new CustomerDto("c2", "Bob", "bob@email.com", "1234567891", "Pass@123");
        DeliveryPersonDto dp = new DeliveryPersonDto("dp2", "DP2", "dp2@email.com", "8888888888");
        Map<FoodItemDto, Integer> items = new HashMap<>();
        OrderDto order = new OrderDto(customer, dp, items);

        double total = OrderCostUtil.calculateTotalCost(order);
        assertEquals(0.0, total, 0.001);
    }
}
