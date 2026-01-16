package com.abes.fdms.dao;

import com.abes.fdms.dto.FoodItemDto;
import com.abes.fdms.util.CollectionUtil;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class FoodItemDaoImplTest {

    private FoodItemDaoImpl dao;

    @BeforeEach
    void setUp() {
        CollectionUtil.reset();
        dao = new FoodItemDaoImpl();
    }

    @Test
    void testAddAndGetFoodItem() {
        FoodItemDto item = new FoodItemDto("Pizza", 100.0);
        dao.addFoodItem(item, 10);
        assertEquals(item, dao.getFoodItemByName("Pizza"));
        assertEquals(10, dao.getInventory().get(item));
    }

    @Test
    void testRemoveFoodItem() {
        FoodItemDto item = new FoodItemDto("Burger", 50.0);
        dao.addFoodItem(item, 5);
        dao.removeFoodItem("Burger");
        assertNull(dao.getFoodItemByName("Burger"));
    }

    @Test
    void testGetInventory() {
        FoodItemDto item = new FoodItemDto("Fries", 30.0);
        dao.addFoodItem(item, 7);
        assertTrue(dao.getInventory().containsKey(item));
    }
}
