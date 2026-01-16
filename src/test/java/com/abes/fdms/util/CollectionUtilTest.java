package com.abes.fdms.util;

import com.abes.fdms.dto.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CollectionUtilTest {

    @Test
    void testResetClearsAllCollections() {
        // Add dummy data
        CollectionUtil.customers.put("c1", new CustomerDto("c1", "Test", "t@e.com", "1234567890", "Pass@123"));
        CollectionUtil.managers.put("m1", new ManagerDto("m1", "Manager", "m@e.com", "1111111111", "Man@123"));
        CollectionUtil.deliveryPersons.put("d1", new DeliveryPersonDto("d1", "DP", "d@e.com", "9999999999"));
        CollectionUtil.inventory.put(new FoodItemDto("Pizza", 100.0), 5);
        CollectionUtil.orders.add(new OrderDto(
                new CustomerDto("c1", "Test", "t@e.com", "1234567890", "Pass@123"),
                new DeliveryPersonDto("d1", "DP", "d@e.com", "9999999999"),
                new java.util.HashMap<>()
        ));

        // Reset
        CollectionUtil.reset();

        assertTrue(CollectionUtil.customers.isEmpty());
        assertTrue(CollectionUtil.managers.isEmpty());
        assertTrue(CollectionUtil.deliveryPersons.isEmpty());
        assertTrue(CollectionUtil.inventory.isEmpty());
        assertTrue(CollectionUtil.orders.isEmpty());
    }
}
