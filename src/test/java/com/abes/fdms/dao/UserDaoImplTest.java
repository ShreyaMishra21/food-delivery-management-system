package com.abes.fdms.dao;

import com.abes.fdms.dto.CustomerDto;
import com.abes.fdms.dto.ManagerDto;
import com.abes.fdms.util.CollectionUtil;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoImplTest {

    private UserDaoImpl userDao;

    @BeforeEach
    void setUp() {
        CollectionUtil.reset();
        userDao = new UserDaoImpl();
    }

    @Test
    void testAddAndGetCustomer() {
        CustomerDto customer = new CustomerDto("c1", "Alice", "alice@email.com", "1234567890", "Pass@123");
        userDao.addCustomer(customer);
        assertEquals(customer, userDao.getCustomerById("c1"));
    }

    @Test
    void testGetCustomers() {
        CustomerDto customer = new CustomerDto("c2", "Bob", "bob@email.com", "1234567891", "Pass@123");
        userDao.addCustomer(customer);
        assertTrue(userDao.getCustomers().containsKey("c2"));
    }

    @Test
    void testGetManagerById() {
        ManagerDto manager = new ManagerDto("m1", "Manager", "m@email.com", "1111111111", "Man@123");
        CollectionUtil.managers.put("m1", manager);
        assertEquals(manager, userDao.getManagerById("m1"));
        assertNull(userDao.getManagerById("notfound"));
    }
}
