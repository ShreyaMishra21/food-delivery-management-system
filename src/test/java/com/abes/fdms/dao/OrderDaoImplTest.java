package com.abes.fdms.dao;

import com.abes.fdms.dto.*;
import com.abes.fdms.util.CollectionUtil;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class OrderDaoImplTest {

    private OrderDaoImpl dao;

    @BeforeEach
    void setUp() {
        CollectionUtil.reset();
        dao = new OrderDaoImpl();
    }

    @Test
    void testAddAndGetOrders() {
        CustomerDto customer = new CustomerDto("c1", "Alice", "alice@email.com", "1234567890", "Pass@123");
        DeliveryPersonDto dp = new DeliveryPersonDto("dp1", "DP", "dp@email.com", "9999999999");
        Map<FoodItemDto, Integer> items = new HashMap<>();
        items.put(new FoodItemDto("Pizza", 100.0), 2);
        OrderDto order = new OrderDto(customer, dp, items);

        dao.addOrder(order);
        assertTrue(dao.getOrders().contains(order));
    }
}
