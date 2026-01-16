package com.abes.fdms.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InputValidationUtilTest {

    @Test
    void testValidEmail() {
        assertTrue(InputValidationUtil.isValidEmail("test@example.com"));
        assertFalse(InputValidationUtil.isValidEmail("invalid-email"));
        assertFalse(InputValidationUtil.isValidEmail("test@.com"));
        assertFalse(InputValidationUtil.isValidEmail(null));
    }

    @Test
    void testValidPhoneNumber() {
        assertTrue(InputValidationUtil.isValidPhoneNumber("1234567890"));
        assertFalse(InputValidationUtil.isValidPhoneNumber("12345"));
        assertFalse(InputValidationUtil.isValidPhoneNumber("abcdefghij"));
        assertFalse(InputValidationUtil.isValidPhoneNumber(null));
    }

    @Test
    void testValidName() {
        assertTrue(InputValidationUtil.isValidName("John Doe"));
        assertFalse(InputValidationUtil.isValidName("12345"));
        assertFalse(InputValidationUtil.isValidName(""));
        assertFalse(InputValidationUtil.isValidName(null));
        assertFalse(InputValidationUtil.isValidName("   "));
    }

    @Test
    void testValidPassword() {
        assertTrue(InputValidationUtil.isValidPassword("Abc@123"));
        assertFalse(InputValidationUtil.isValidPassword("abc123")); // no uppercase, no special
        assertFalse(InputValidationUtil.isValidPassword("ABC@123")); // no lowercase
        assertFalse(InputValidationUtil.isValidPassword("Abcdefg")); // no digit, no special
        assertFalse(InputValidationUtil.isValidPassword("Abc@1"));   // too short
        assertFalse(InputValidationUtil.isValidPassword(null));
    }
}
