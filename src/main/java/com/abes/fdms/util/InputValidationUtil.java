package com.abes.fdms.util;

import java.util.regex.Pattern;

/**
 * Utility class for validating user input such as email, phone number, name, and password.
 */
public class InputValidationUtil {
    // Email must be in the format: something@domain.extension
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
        "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$"
    );

    // Phone number must be exactly 10 digits
    private static final Pattern PHONE_PATTERN = Pattern.compile(
        "^\\d{10}$"
    );

    //Name must contain only letters and spaces, and cannot be only digits or empty
    private static final Pattern NAME_PATTERN = Pattern.compile(
        "^(?!\\d+$)[A-Za-z\\s]+$"
    );

    // Password must be at least 6 characters, with at least 1 uppercase, 1 lowercase, 1 digit, and 1 special character
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(
        "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]).{6,}$"
    );

    
    //Validates email format
    public static boolean isValidEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }


    //Validates phone number format (10 digits)
    public static boolean isValidPhoneNumber(String phone) {
        return phone != null && PHONE_PATTERN.matcher(phone).matches();
    }

   
    //Validates name (letters and spaces only, not empty or only digits)
    public static boolean isValidName(String name) {
        return name != null && !name.trim().isEmpty() && NAME_PATTERN.matcher(name.trim()).matches();
    }

   
    //Validates password strength.
    public static boolean isValidPassword(String password) {
        return password != null && PASSWORD_PATTERN.matcher(password).matches();
    }
}
