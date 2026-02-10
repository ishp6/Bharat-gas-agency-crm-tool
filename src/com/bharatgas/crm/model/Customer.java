package com.bharatgas.crm.model;

import com.bharatgas.crm.enums.ConnectionType;
import com.bharatgas.crm.enums.ConnectionStatus;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Bharat Gas customer with a gas connection.
 * Demonstrates: Inheritance (extends Person), Polymorphism (overrides
 * displayDetails),
 * Encapsulation (private fields with getters/setters).
 */
public class Customer extends Person {
    private String customerId;
    private ConnectionType connectionType;
    private ConnectionStatus connectionStatus;
    private LocalDate registrationDate;
    private List<Booking> bookings;

    public Customer(String customerId, String name, String phone, String address,
            String email, ConnectionType connectionType) {
        super(name, phone, address, email);
        this.customerId = customerId;
        this.connectionType = connectionType;
        this.connectionStatus = ConnectionStatus.ACTIVE;
        this.registrationDate = LocalDate.now();
        this.bookings = new ArrayList<>();
    }

    // Polymorphism — overriding abstract method from Person
    @Override
    public void displayDetails() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║               CUSTOMER DETAILS                          ║");
        System.out.println("╠══════════════════════════════════════════════════════════╣");
        System.out.printf("║  Customer ID     : %-37s║%n", customerId);
        System.out.printf("║  Name            : %-37s║%n", getName());
        System.out.printf("║  Phone           : %-37s║%n", getPhone());
        System.out.printf("║  Email           : %-37s║%n", getEmail());
        System.out.printf("║  Address         : %-37s║%n", getAddress());
        System.out.printf("║  Connection Type : %-37s║%n", connectionType.getDisplayName());
        System.out.printf("║  Status          : %-37s║%n", connectionStatus.getDisplayName());
        System.out.printf("║  Registered On   : %-37s║%n", registrationDate.format(fmt));
        System.out.printf("║  Total Bookings  : %-37d║%n", bookings.size());
        System.out.println("╚══════════════════════════════════════════════════════════╝");
    }

    // Getters and Setters
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public ConnectionType getConnectionType() {
        return connectionType;
    }

    public void setConnectionType(ConnectionType connectionType) {
        this.connectionType = connectionType;
    }

    public ConnectionStatus getConnectionStatus() {
        return connectionStatus;
    }

    public void setConnectionStatus(ConnectionStatus connectionStatus) {
        this.connectionStatus = connectionStatus;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void addBooking(Booking booking) {
        this.bookings.add(booking);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s | %s | %s | %s",
                customerId, getName(), getPhone(), connectionType, connectionStatus);
    }
}
