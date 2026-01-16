package com.abes.fdms.service;

import com.abes.fdms.dto.FoodItemDto;
import com.abes.fdms.exception.DuplicateItemException;
import com.abes.fdms.util.CollectionUtil;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class FoodServiceTest {

    private FoodService foodService;

    @BeforeEach
    void setUp() {
        CollectionUtil.reset(); // Ensure clean state before each test
        foodService = new FoodService();
    }

    @Test
    void testAddNewItem_Success() throws Exception {
        foodService.addNewItem("TestPizza", 120.0, 5);
        FoodItemDto item = foodService.getFoodItemByName("TestPizza");
        assertNotNull(item);
        assertEquals("TestPizza", item.getName());
    }

    @Test
    void testAddNewItem_AlreadyExists() throws Exception {
        foodService.addNewItem("TestBurger", 80.0, 3);
        assertThrows(DuplicateItemException.class, () -> {
            foodService.addNewItem("TestBurger", 90.0, 2);
        });
    }

    @Test
    void testRestockItem() throws Exception {
        foodService.addNewItem("TestFries", 50.0, 2);
        foodService.restockItem("TestFries", 3);
        assertEquals(5, foodService.getMenu().get(foodService.getFoodItemByName("TestFries")));
    }

    @Test
    void testRemoveItem() throws Exception {
        foodService.addNewItem("TestRemove", 60.0, 1);
        foodService.removeItem("TestRemove");
        assertNull(foodService.getFoodItemByName("TestRemove"));
    }
}
