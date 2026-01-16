package com.abes.fdms.util;

import com.abes.fdms.dto.*;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class OrderFormatterUtilTest {

    @Test
    void testFormatOrderDetails() {
        CustomerDto customer = new CustomerDto("c1", "Alice", "alice@email.com", "1234567890", "Pass@123");
        DeliveryPersonDto dp = new DeliveryPersonDto("dp1", "DP", "dp@email.com", "9999999999");
        Map<FoodItemDto, Integer> items = new HashMap<>();
        items.put(new FoodItemDto("Pizza", 100.0), 2);
        items.put(new FoodItemDto("Burger", 50.0), 1);
        OrderDto order = new OrderDto(customer, dp, items);

        String formatted = OrderFormatterUtil.formatOrderDetails(order);

        assertTrue(formatted.contains("Order for Alice"));
        assertTrue(formatted.contains("Pizza- Rs. 100.0 x 2"));
        assertTrue(formatted.contains("Burger- Rs. 50.0 x 1"));
        assertTrue(formatted.contains("Total Cost: Rs 250.00"));
        assertTrue(formatted.contains("Status: Placed"));
    }
}
