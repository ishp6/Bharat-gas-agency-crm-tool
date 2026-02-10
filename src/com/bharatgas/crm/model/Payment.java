package com.bharatgas.crm.model;

import com.bharatgas.crm.enums.PaymentMode;
import com.bharatgas.crm.enums.PaymentStatus;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a payment made against a cylinder booking.
 * Demonstrates: Encapsulation, Composition (has-a Booking).
 */
public class Payment {
    private String paymentId;
    private Booking booking;
    private double amount;
    private PaymentMode paymentMode;
    private PaymentStatus paymentStatus;
    private LocalDate paymentDate;

    public Payment(String paymentId, Booking booking, double amount, PaymentMode paymentMode) {
        this.paymentId = paymentId;
        this.booking = booking;
        this.amount = amount;
        this.paymentMode = paymentMode;
        this.paymentStatus = PaymentStatus.COMPLETED;
        this.paymentDate = LocalDate.now();
    }

    public void displayDetails() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        System.out.println("┌──────────────────────────────────────────────────────────┐");
        System.out.println("│               PAYMENT DETAILS                            │");
        System.out.println("├──────────────────────────────────────────────────────────┤");
        System.out.printf("│  Payment ID   : %-40s│%n", paymentId);
        System.out.printf("│  Booking ID   : %-40s│%n", booking.getBookingId());
        System.out.printf("│  Customer     : %-40s│%n", booking.getCustomer().getName());
        System.out.printf("│  Amount       : ₹%-39.2f│%n", amount);
        System.out.printf("│  Payment Mode : %-40s│%n", paymentMode.getDisplayName());
        System.out.printf("│  Status       : %-40s│%n", paymentStatus.getDisplayName());
        System.out.printf("│  Date         : %-40s│%n", paymentDate.format(fmt));
        System.out.println("└──────────────────────────────────────────────────────────┘");
    }

    // Getters and Setters
    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public PaymentMode getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(PaymentMode paymentMode) {
        this.paymentMode = paymentMode;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    @Override
    public String toString() {
        return String.format("[%s] Booking: %s | ₹%.2f | %s | %s",
                paymentId, booking.getBookingId(), amount, paymentMode, paymentStatus);
    }
}
