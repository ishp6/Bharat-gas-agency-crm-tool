package com.bharatgas.crm.model;

import com.bharatgas.crm.enums.ComplaintStatus;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a customer complaint filed with the agency.
 * Demonstrates: Encapsulation, Composition (has-a Customer).
 */
public class Complaint {
    private String complaintId;
    private Customer customer;
    private String description;
    private ComplaintStatus status;
    private LocalDate filedDate;
    private LocalDate resolvedDate;

    public Complaint(String complaintId, Customer customer, String description) {
        this.complaintId = complaintId;
        this.customer = customer;
        this.description = description;
        this.status = ComplaintStatus.OPEN;
        this.filedDate = LocalDate.now();
        this.resolvedDate = null;
    }

    public void displayDetails() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        System.out.println("┌──────────────────────────────────────────────────────────┐");
        System.out.println("│               COMPLAINT DETAILS                          │");
        System.out.println("├──────────────────────────────────────────────────────────┤");
        System.out.printf("│  Complaint ID  : %-39s│%n", complaintId);
        System.out.printf("│  Customer      : %-39s│%n", customer.getName() + " (" + customer.getCustomerId() + ")");
        System.out.printf("│  Description   : %-39s│%n", description);
        System.out.printf("│  Status        : %-39s│%n", status.getDisplayName());
        System.out.printf("│  Filed On      : %-39s│%n", filedDate.format(fmt));
        System.out.printf("│  Resolved On   : %-39s│%n",
                resolvedDate != null ? resolvedDate.format(fmt) : "Not yet resolved");
        System.out.println("└──────────────────────────────────────────────────────────┘");
    }

    // Getters and Setters
    public String getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(String complaintId) {
        this.complaintId = complaintId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ComplaintStatus getStatus() {
        return status;
    }

    public void setStatus(ComplaintStatus status) {
        this.status = status;
    }

    public LocalDate getFiledDate() {
        return filedDate;
    }

    public void setFiledDate(LocalDate filedDate) {
        this.filedDate = filedDate;
    }

    public LocalDate getResolvedDate() {
        return resolvedDate;
    }

    public void setResolvedDate(LocalDate resolvedDate) {
        this.resolvedDate = resolvedDate;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s | %s | %s | Filed: %s",
                complaintId, customer.getName(), description, status, filedDate);
    }
}
