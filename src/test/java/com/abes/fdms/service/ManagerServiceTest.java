package com.abes.fdms.service;

import com.abes.fdms.dto.ManagerDto;
import com.abes.fdms.util.CollectionUtil;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class ManagerServiceTest {

    private ManagerService managerService;

    @BeforeEach
    void setUp() {
        CollectionUtil.reset();
        // Add a dummy manager for login tests
        CollectionUtil.managers.put("m1", new ManagerDto("m1", "Manager One", "m1@email.com", "1111111111", "Pass@123"));
        managerService = new ManagerService();
    }

    @Test
    void testLoginManager_Success() {
        ManagerDto manager = managerService.loginManager("m1", "Pass@123");
        assertNotNull(manager);
        assertEquals("m1", manager.getId());
    }

    @Test
    void testLoginManager_WrongPassword() {
        ManagerDto manager = managerService.loginManager("m1", "WrongPass");
        assertNull(manager);
    }

    @Test
    void testLoginManager_NonExistentId() {
        ManagerDto manager = managerService.loginManager("notfound", "Pass@123");
        assertNull(manager);
    }
}
