package com.bharatgas.crm.model;

import com.bharatgas.crm.enums.UserRole;

/**
 * Represents an employee of the Bharat Gas Agency.
 * Demonstrates: Inheritance (extends Person), Polymorphism (overrides
 * displayDetails).
 */
public class Employee extends Person {
    private String employeeId;
    private UserRole role;
    private double salary;

    public Employee(String employeeId, String name, String phone, String address,
            String email, UserRole role, double salary) {
        super(name, phone, address, email);
        this.employeeId = employeeId;
        this.role = role;
        this.salary = salary;
    }

    // Polymorphism — overriding abstract method from Person
    @Override
    public void displayDetails() {
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║               EMPLOYEE DETAILS                          ║");
        System.out.println("╠══════════════════════════════════════════════════════════╣");
        System.out.printf("║  Employee ID : %-41s║%n", employeeId);
        System.out.printf("║  Name        : %-41s║%n", getName());
        System.out.printf("║  Phone       : %-41s║%n", getPhone());
        System.out.printf("║  Email       : %-41s║%n", getEmail());
        System.out.printf("║  Role        : %-41s║%n", role.getDisplayName());
        System.out.printf("║  Salary      : ₹%-40.2f║%n", salary);
        System.out.println("╚══════════════════════════════════════════════════════════╝");
    }

    // Getters and Setters
    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s | %s | %s", employeeId, getName(), role, getPhone());
    }
}
