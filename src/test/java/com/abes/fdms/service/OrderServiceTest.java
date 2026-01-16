package com.abes.fdms.service;

import com.abes.fdms.dto.*;

import com.abes.fdms.exception.*;
import com.abes.fdms.util.CollectionUtil;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    private OrderService orderService;
    private CustomerService customerService;
    private FoodService foodService;
    private DeliveryService deliveryService;

    private CustomerDto customer;

    @BeforeEach
    void setUp() throws Exception {
        CollectionUtil.reset(); // Ensure clean state before each test
        orderService = new OrderService();
        customerService = new CustomerService();
        foodService = new FoodService();
        deliveryService = new DeliveryService();

        // Register a delivery person so that one is available for orders
        deliveryService.registerDeliveryPerson("dp1", "Delivery Guy", "dp1@email.com", "9999999999");

        customerService.registerCustomer("order1", "Order User", "order1@email.com", "1234567897", "Pass@123");
        customer = customerService.loginCustomer("order1", "Pass@123");
        foodService.addNewItem("OrderPizza", 100.0, 5);
    }

    @Test
    void testPlaceOrder_Success() throws Exception {
        Map<String, Integer> requested = new HashMap<>();
        requested.put("OrderPizza", 2);
        OrderDto order = orderService.placeOrder(customer, requested);
        assertNotNull(order);
        assertEquals(customer, order.getCustomer());
        assertEquals(2, order.getItemsOrdered().get(foodService.getFoodItemByName("OrderPizza")));
    }

    @Test
    void testPlaceOrder_InvalidQuantity() {
        Map<String, Integer> requested = new HashMap<>();
        requested.put("OrderPizza", 0);
        assertThrows(InvalidQuantityException.class, () -> {
            orderService.placeOrder(customer, requested);
        });
    }

    @Test
    void testPlaceOrder_ItemNotFound() {
        Map<String, Integer> requested = new HashMap<>();
        requested.put("NonExistent", 1);
        assertThrows(ItemNotFoundException.class, () -> {
            orderService.placeOrder(customer, requested);
        });
    }

    @Test
    void testPlaceOrder_InsufficientStock() {
        Map<String, Integer> requested = new HashMap<>();
        requested.put("OrderPizza", 100);
        assertThrows(InvalidOrderException.class, () -> {
            orderService.placeOrder(customer, requested);
        });
    }
}
