package com.bharatgas.crm.util;

/**
 * Utility class for generating unique IDs for various entities.
 * Uses static counters with a prefix pattern (e.g., BG-CUST-001).
 */
public class IDGenerator {
    private static int customerCounter = 0;
    private static int bookingCounter = 0;
    private static int paymentCounter = 0;
    private static int complaintCounter = 0;
    private static int employeeCounter = 0;

    public static String generateCustomerId() {
        customerCounter++;
        return String.format("BG-CUST-%03d", customerCounter);
    }

    public static String generateBookingId() {
        bookingCounter++;
        return String.format("BG-BK-%03d", bookingCounter);
    }

    public static String generatePaymentId() {
        paymentCounter++;
        return String.format("BG-PAY-%03d", paymentCounter);
    }

    public static String generateComplaintId() {
        complaintCounter++;
        return String.format("BG-CMP-%03d", complaintCounter);
    }

    public static String generateEmployeeId() {
        employeeCounter++;
        return String.format("BG-EMP-%03d", employeeCounter);
    }

    // Allow setting counters (useful when loading existing data)
    public static void setCustomerCounter(int count) {
        customerCounter = count;
    }

    public static void setBookingCounter(int count) {
        bookingCounter = count;
    }

    public static void setPaymentCounter(int count) {
        paymentCounter = count;
    }

    public static void setComplaintCounter(int count) {
        complaintCounter = count;
    }

    public static void setEmployeeCounter(int count) {
        employeeCounter = count;
    }
}
