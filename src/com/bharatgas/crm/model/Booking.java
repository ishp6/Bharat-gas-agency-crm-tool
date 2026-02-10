package com.bharatgas.crm.model;

import com.bharatgas.crm.enums.BookingStatus;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a cylinder refill booking by a customer.
 * Demonstrates: Encapsulation, Composition (has-a Customer and Cylinder).
 */
public class Booking {
    private String bookingId;
    private Customer customer;
    private Cylinder cylinder;
    private LocalDate bookingDate;
    private LocalDate expectedDeliveryDate;
    private LocalDate actualDeliveryDate;
    private BookingStatus status;

    public Booking(String bookingId, Customer customer, Cylinder cylinder) {
        this.bookingId = bookingId;
        this.customer = customer;
        this.cylinder = cylinder;
        this.bookingDate = LocalDate.now();
        this.expectedDeliveryDate = LocalDate.now().plusDays(3);
        this.actualDeliveryDate = null;
        this.status = BookingStatus.PENDING;
    }

    public void displayDetails() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        System.out.println("┌──────────────────────────────────────────────────────────┐");
        System.out.println("│               BOOKING DETAILS                            │");
        System.out.println("├──────────────────────────────────────────────────────────┤");
        System.out.printf("│  Booking ID       : %-36s│%n", bookingId);
        System.out.printf("│  Customer         : %-36s│%n", customer.getName() + " (" + customer.getCustomerId() + ")");
        System.out.printf("│  Cylinder         : %-36s│%n", cylinder.toString());
        System.out.printf("│  Booking Date     : %-36s│%n", bookingDate.format(fmt));
        System.out.printf("│  Expected Delivery: %-36s│%n", expectedDeliveryDate.format(fmt));
        System.out.printf("│  Actual Delivery  : %-36s│%n",
                actualDeliveryDate != null ? actualDeliveryDate.format(fmt) : "Not yet delivered");
        System.out.printf("│  Status           : %-36s│%n", status.getDisplayName());
        System.out.println("└──────────────────────────────────────────────────────────┘");
    }

    // Getters and Setters
    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Cylinder getCylinder() {
        return cylinder;
    }

    public void setCylinder(Cylinder cylinder) {
        this.cylinder = cylinder;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public LocalDate getExpectedDeliveryDate() {
        return expectedDeliveryDate;
    }

    public void setExpectedDeliveryDate(LocalDate expectedDeliveryDate) {
        this.expectedDeliveryDate = expectedDeliveryDate;
    }

    public LocalDate getActualDeliveryDate() {
        return actualDeliveryDate;
    }

    public void setActualDeliveryDate(LocalDate actualDeliveryDate) {
        this.actualDeliveryDate = actualDeliveryDate;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s → %s | %s | %s",
                bookingId, customer.getName(), cylinder.getCylinderType(),
                bookingDate, status);
    }
}
