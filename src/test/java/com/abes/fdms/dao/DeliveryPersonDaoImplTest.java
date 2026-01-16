package com.abes.fdms.dao;

import com.abes.fdms.dto.DeliveryPersonDto;

import com.abes.fdms.util.CollectionUtil;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class DeliveryPersonDaoImplTest {

    private DeliveryPersonDaoImpl dao;

    @BeforeEach
    void setUp() {
        CollectionUtil.reset();
        dao = new DeliveryPersonDaoImpl();
    }

    @Test
    void testAddAndGetDeliveryPerson() {
        DeliveryPersonDto dp = new DeliveryPersonDto("dp1", "DP One", "dp1@email.com", "9999999999");
        dao.addDeliveryPerson(dp);
        assertEquals(dp, dao.getDeliveryPersonById("dp1"));
    }

    @Test
    void testGetDeliveryPersons() {
        DeliveryPersonDto dp = new DeliveryPersonDto("dp2", "DP Two", "dp2@email.com", "8888888888");
        dao.addDeliveryPerson(dp);
        assertTrue(dao.getDeliveryPersons().containsKey("dp2"));
    }

    @Test
    void testRemoveDeliveryPerson() {
        DeliveryPersonDto dp = new DeliveryPersonDto("dp3", "DP Three", "dp3@email.com", "7777777777");
        dao.addDeliveryPerson(dp);
        dao.removeDeliveryPerson("dp3");
        assertNull(dao.getDeliveryPersonById("dp3"));
    }
}
