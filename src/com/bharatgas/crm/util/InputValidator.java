package com.bharatgas.crm.util;

import java.util.regex.Pattern;

/**
 * Utility class for validating user input.
 * Demonstrates: Static utility methods, input sanitization.
 */
public class InputValidator {

    private static final Pattern PHONE_PATTERN = Pattern.compile("^[6-9]\\d{9}$");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w.-]+@[\\w.-]+\\.\\w{2,}$");
    private static final Pattern NAME_PATTERN = Pattern.compile("^[A-Za-z ]{2,50}$");

    /**
     * Validates an Indian mobile number (10 digits starting with 6-9).
     */
    public static boolean isValidPhone(String phone) {
        return phone != null && PHONE_PATTERN.matcher(phone.trim()).matches();
    }

    /**
     * Validates an email address format.
     */
    public static boolean isValidEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email.trim()).matches();
    }

    /**
     * Validates a person's name (alphabets and spaces, 2-50 chars).
     */
    public static boolean isValidName(String name) {
        return name != null && NAME_PATTERN.matcher(name.trim()).matches();
    }

    /**
     * Checks if a string is non-null and non-empty.
     */
    public static boolean isNotEmpty(String str) {
        return str != null && !str.trim().isEmpty();
    }

    /**
     * Validates that a number is within a specified range (inclusive).
     */
    public static boolean isInRange(int value, int min, int max) {
        return value >= min && value <= max;
    }
}
