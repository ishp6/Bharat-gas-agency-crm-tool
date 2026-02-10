package com.bharatgas.crm.model;

/**
 * Abstract base class representing a person.
 * Demonstrates: Abstraction, Encapsulation, and serves as base for Inheritance.
 */
public abstract class Person {
    private String name;
    private String phone;
    private String address;
    private String email;

    // Constructor
    public Person(String name, String phone, String address, String email) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.email = email;
    }

    // Abstract method — subclasses must implement (Polymorphism)
    public abstract void displayDetails();

    // Getters and Setters (Encapsulation)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return String.format("Name: %s | Phone: %s | Email: %s | Address: %s",
                name, phone, email, address);
    }
}
