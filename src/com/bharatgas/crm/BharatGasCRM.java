package com.bharatgas.crm;

import com.bharatgas.crm.model.*;
import com.bharatgas.crm.enums.*;
import com.bharatgas.crm.service.*;
import com.bharatgas.crm.util.*;

import java.util.List;
import java.util.Scanner;

/**
 * ╔══════════════════════════════════════════════════════════════╗
 * ║ BHARAT GAS AGENCY — CRM TOOL (Console App) ║
 * ║ Built using Java OOP Concepts · Menu-Driven Interface ║
 * ╚══════════════════════════════════════════════════════════════╝
 *
 * Main application class demonstrating:
 * - Abstraction (interfaces, abstract classes)
 * - Encapsulation (private fields, getters/setters)
 * - Inheritance (Person → Customer / Employee)
 * - Polymorphism (method overriding, interface implementations)
 * - Composition (Booking has-a Customer, has-a Cylinder)
 */
public class BharatGasCRM {

    private static final Scanner scanner = new Scanner(System.in);
    private static final CustomerService customerService = new CustomerService();
    private static final BookingService bookingService = new BookingService();
    private static final PaymentService paymentService = new PaymentService();
    private static final ComplaintService complaintService = new ComplaintService();

    // ─────────────────────────────── MAIN ───────────────────────────────

    public static void main(String[] args) {
        loadSampleData();
        showWelcomeBanner();

        boolean running = true;
        while (running) {
            showMainMenu();
            int choice = readIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    customerManagementMenu();
                    break;
                case 2:
                    cylinderBookingMenu();
                    break;
                case 3:
                    paymentManagementMenu();
                    break;
                case 4:
                    complaintManagementMenu();
                    break;
                case 5:
                    reportsAndDashboard();
                    break;
                case 0:
                    running = false;
                    System.out.println("\n🙏 Thank you for using Bharat Gas CRM. Goodbye!\n");
                    break;
                default:
                    System.out.println("❌ Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    // ─────────────────────── WELCOME & MENUS ────────────────────────────

    private static void showWelcomeBanner() {
        System.out.println();
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                                                              ║");
        System.out.println("║       🔥  BHARAT GAS AGENCY — CRM MANAGEMENT SYSTEM  🔥     ║");
        System.out.println("║                                                              ║");
        System.out.println("║       Your Trusted Partner for Safe & Clean Energy           ║");
        System.out.println("║       ──────────────────────────────────────────────          ║");
        System.out.println("║       Manage Customers · Bookings · Payments · Complaints    ║");
        System.out.println("║                                                              ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
        System.out.println();
    }

    private static void showMainMenu() {
        System.out.println();
        System.out.println("┌──────────────────────────────────────────┐");
        System.out.println("│            📋 MAIN MENU                  │");
        System.out.println("├──────────────────────────────────────────┤");
        System.out.println("│  1. 👤 Customer Management               │");
        System.out.println("│  2. 🛢️  Cylinder Booking                  │");
        System.out.println("│  3. 💰 Payment Management                │");
        System.out.println("│  4. 📝 Complaint Management              │");
        System.out.println("│  5. 📊 Reports & Dashboard               │");
        System.out.println("│  0. 🚪 Exit                              │");
        System.out.println("└──────────────────────────────────────────┘");
    }

    // ──────────────────── CUSTOMER MANAGEMENT ───────────────────────────

    private static void customerManagementMenu() {
        boolean back = false;
        while (!back) {
            System.out.println();
            System.out.println("┌──────────────────────────────────────────┐");
            System.out.println("│       👤 CUSTOMER MANAGEMENT             │");
            System.out.println("├──────────────────────────────────────────┤");
            System.out.println("│  1. Register New Customer                │");
            System.out.println("│  2. View All Customers                   │");
            System.out.println("│  3. Search Customer by Name              │");
            System.out.println("│  4. View Customer Details (by ID)        │");
            System.out.println("│  5. Update Customer Information          │");
            System.out.println("│  6. Deactivate Customer Connection       │");
            System.out.println("│  0. ← Back to Main Menu                 │");
            System.out.println("└──────────────────────────────────────────┘");

            int choice = readIntInput("Enter your choice: ");
            switch (choice) {
                case 1:
                    registerCustomer();
                    break;
                case 2:
                    viewAllCustomers();
                    break;
                case 3:
                    searchCustomerByName();
                    break;
                case 4:
                    viewCustomerById();
                    break;
                case 5:
                    updateCustomer();
                    break;
                case 6:
                    deactivateCustomer();
                    break;
                case 0:
                    back = true;
                    break;
                default:
                    System.out.println("❌ Invalid choice.");
            }
        }
    }

    private static void registerCustomer() {
        System.out.println("\n── Register New Customer ──────────────────");

        String name = readValidatedInput("Enter Name: ", "name");
        String phone = readValidatedInput("Enter Phone (10 digits): ", "phone");
        String email = readValidatedInput("Enter Email: ", "email");
        System.out.print("Enter Address: ");
        String address = scanner.nextLine().trim();

        System.out.println("Connection Type:");
        System.out.println("  1. Domestic");
        System.out.println("  2. Commercial");
        int typeChoice = readIntInput("Select type: ");
        ConnectionType connType = (typeChoice == 2) ? ConnectionType.COMMERCIAL : ConnectionType.DOMESTIC;

        String customerId = IDGenerator.generateCustomerId();
        Customer customer = new Customer(customerId, name, phone, address, email, connType);
        customerService.add(customer);
        customer.displayDetails();
    }

    private static void viewAllCustomers() {
        List<Customer> customers = customerService.getAll();
        if (customers.isEmpty()) {
            System.out.println("\n📭 No customers registered yet.");
            return;
        }
        System.out.println("\n── All Customers (" + customers.size() + ") ──────────────────");
        System.out.printf("%-14s %-20s %-14s %-12s %-10s%n",
                "ID", "Name", "Phone", "Type", "Status");
        System.out.println(repeatChar('─', 70));
        for (Customer c : customers) {
            System.out.printf("%-14s %-20s %-14s %-12s %-10s%n",
                    c.getCustomerId(), c.getName(), c.getPhone(),
                    c.getConnectionType(), c.getConnectionStatus());
        }
    }

    private static void searchCustomerByName() {
        System.out.print("\nEnter name to search: ");
        String name = scanner.nextLine().trim();
        List<Customer> results = customerService.searchByName(name);
        if (results.isEmpty()) {
            System.out.println("📭 No customers found matching: " + name);
        } else {
            System.out.println("\n── Search Results (" + results.size() + ") ──────────────");
            for (Customer c : results) {
                System.out.println("  " + c);
            }
        }
    }

    private static void viewCustomerById() {
        System.out.print("\nEnter Customer ID: ");
        String id = scanner.nextLine().trim();
        Customer customer = customerService.getById(id);
        if (customer != null) {
            // Polymorphism — calling overridden displayDetails()
            customer.displayDetails();
        } else {
            System.out.println("❌ Customer not found with ID: " + id);
        }
    }

    private static void updateCustomer() {
        System.out.print("\nEnter Customer ID to update: ");
        String id = scanner.nextLine().trim();
        Customer customer = customerService.getById(id);
        if (customer == null) {
            System.out.println("❌ Customer not found.");
            return;
        }

        System.out.println("Current details:");
        customer.displayDetails();

        System.out.println("\nLeave blank to keep current value.");

        System.out.print("New Name [" + customer.getName() + "]: ");
        String name = scanner.nextLine().trim();
        if (!name.isEmpty() && InputValidator.isValidName(name))
            customer.setName(name);

        System.out.print("New Phone [" + customer.getPhone() + "]: ");
        String phone = scanner.nextLine().trim();
        if (!phone.isEmpty() && InputValidator.isValidPhone(phone))
            customer.setPhone(phone);

        System.out.print("New Email [" + customer.getEmail() + "]: ");
        String email = scanner.nextLine().trim();
        if (!email.isEmpty() && InputValidator.isValidEmail(email))
            customer.setEmail(email);

        System.out.print("New Address [" + customer.getAddress() + "]: ");
        String address = scanner.nextLine().trim();
        if (!address.isEmpty())
            customer.setAddress(address);

        System.out.println("✅ Customer updated successfully!");
        customer.displayDetails();
    }

    private static void deactivateCustomer() {
        System.out.print("\nEnter Customer ID to deactivate: ");
        String id = scanner.nextLine().trim();
        customerService.delete(id);
    }

    // ──────────────────── CYLINDER BOOKING ──────────────────────────────

    private static void cylinderBookingMenu() {
        boolean back = false;
        while (!back) {
            System.out.println();
            System.out.println("┌──────────────────────────────────────────┐");
            System.out.println("│       🛢️  CYLINDER BOOKING               │");
            System.out.println("├──────────────────────────────────────────┤");
            System.out.println("│  1. Book Cylinder Refill                 │");
            System.out.println("│  2. View All Bookings                    │");
            System.out.println("│  3. View Booking Details (by ID)         │");
            System.out.println("│  4. Update Delivery Status               │");
            System.out.println("│  5. Cancel Booking                       │");
            System.out.println("│  6. View Bookings by Customer            │");
            System.out.println("│  0. ← Back to Main Menu                 │");
            System.out.println("└──────────────────────────────────────────┘");

            int choice = readIntInput("Enter your choice: ");
            switch (choice) {
                case 1:
                    bookCylinder();
                    break;
                case 2:
                    viewAllBookings();
                    break;
                case 3:
                    viewBookingById();
                    break;
                case 4:
                    updateDeliveryStatus();
                    break;
                case 5:
                    cancelBooking();
                    break;
                case 6:
                    viewBookingsByCustomer();
                    break;
                case 0:
                    back = true;
                    break;
                default:
                    System.out.println("❌ Invalid choice.");
            }
        }
    }

    private static void bookCylinder() {
        System.out.println("\n── Book Cylinder Refill ───────────────────");
        System.out.print("Enter Customer ID: ");
        String custId = scanner.nextLine().trim();
        Customer customer = customerService.getById(custId);

        if (customer == null) {
            System.out.println("❌ Customer not found. Please register first.");
            return;
        }

        if (customer.getConnectionStatus() != ConnectionStatus.ACTIVE) {
            System.out.println("❌ Customer connection is not active. Cannot book.");
            return;
        }

        System.out.println("\nSelect Cylinder Type:");
        System.out.println("  1. 14.2 KG Domestic  — ₹903.00");
        System.out.println("  2. 5 KG Domestic     — ₹349.00");
        System.out.println("  3. 19 KG Commercial  — ₹1850.00");
        int cylChoice = readIntInput("Select cylinder: ");

        Cylinder cylinder;
        switch (cylChoice) {
            case 1:
                cylinder = Cylinder.domestic14Kg();
                break;
            case 2:
                cylinder = Cylinder.smallCylinder5Kg();
                break;
            case 3:
                cylinder = Cylinder.freeTradeCommercial19Kg();
                break;
            default:
                System.out.println("❌ Invalid choice. Defaulting to 14.2 KG.");
                cylinder = Cylinder.domestic14Kg();
        }

        String bookingId = IDGenerator.generateBookingId();
        Booking booking = new Booking(bookingId, customer, cylinder);
        bookingService.add(booking);
        customer.addBooking(booking);
        booking.displayDetails();
    }

    private static void viewAllBookings() {
        List<Booking> bookings = bookingService.getAll();
        if (bookings.isEmpty()) {
            System.out.println("\n📭 No bookings yet.");
            return;
        }
        System.out.println("\n── All Bookings (" + bookings.size() + ") ──────────────────");
        System.out.printf("%-12s %-18s %-22s %-14s %-14s%n",
                "Booking ID", "Customer", "Cylinder", "Date", "Status");
        System.out.println(repeatChar('─', 80));
        for (Booking b : bookings) {
            System.out.printf("%-12s %-18s %-22s %-14s %-14s%n",
                    b.getBookingId(), b.getCustomer().getName(),
                    b.getCylinder().getCylinderType(), b.getBookingDate(), b.getStatus());
        }
    }

    private static void viewBookingById() {
        System.out.print("\nEnter Booking ID: ");
        String id = scanner.nextLine().trim();
        Booking booking = bookingService.getById(id);
        if (booking != null) {
            booking.displayDetails();
        } else {
            System.out.println("❌ Booking not found with ID: " + id);
        }
    }

    private static void updateDeliveryStatus() {
        System.out.print("\nEnter Booking ID: ");
        String id = scanner.nextLine().trim();
        Booking booking = bookingService.getById(id);
        if (booking == null) {
            System.out.println("❌ Booking not found.");
            return;
        }

        System.out.println("Current Status: " + booking.getStatus());
        System.out.println("Select New Status:");
        System.out.println("  1. Confirmed");
        System.out.println("  2. Out for Delivery");
        System.out.println("  3. Delivered");
        int statusChoice = readIntInput("Select status: ");

        BookingStatus newStatus;
        switch (statusChoice) {
            case 1:
                newStatus = BookingStatus.CONFIRMED;
                break;
            case 2:
                newStatus = BookingStatus.OUT_FOR_DELIVERY;
                break;
            case 3:
                newStatus = BookingStatus.DELIVERED;
                break;
            default:
                System.out.println("❌ Invalid choice.");
                return;
        }
        bookingService.updateDeliveryStatus(id, newStatus);
    }

    private static void cancelBooking() {
        System.out.print("\nEnter Booking ID to cancel: ");
        String id = scanner.nextLine().trim();
        bookingService.delete(id);
    }

    private static void viewBookingsByCustomer() {
        System.out.print("\nEnter Customer ID: ");
        String custId = scanner.nextLine().trim();
        List<Booking> bookings = bookingService.getByCustomerId(custId);
        if (bookings.isEmpty()) {
            System.out.println("📭 No bookings found for customer: " + custId);
        } else {
            System.out.println("\n── Bookings for " + custId + " ──────────────");
            for (Booking b : bookings) {
                System.out.println("  " + b);
            }
        }
    }

    // ──────────────────── PAYMENT MANAGEMENT ────────────────────────────

    private static void paymentManagementMenu() {
        boolean back = false;
        while (!back) {
            System.out.println();
            System.out.println("┌──────────────────────────────────────────┐");
            System.out.println("│       💰 PAYMENT MANAGEMENT              │");
            System.out.println("├──────────────────────────────────────────┤");
            System.out.println("│  1. Record Payment                       │");
            System.out.println("│  2. View All Payments                    │");
            System.out.println("│  3. View Payment Details (by ID)         │");
            System.out.println("│  4. View Payments by Booking             │");
            System.out.println("│  5. Refund Payment                       │");
            System.out.println("│  0. ← Back to Main Menu                 │");
            System.out.println("└──────────────────────────────────────────┘");

            int choice = readIntInput("Enter your choice: ");
            switch (choice) {
                case 1:
                    recordPayment();
                    break;
                case 2:
                    viewAllPayments();
                    break;
                case 3:
                    viewPaymentById();
                    break;
                case 4:
                    viewPaymentsByBooking();
                    break;
                case 5:
                    refundPayment();
                    break;
                case 0:
                    back = true;
                    break;
                default:
                    System.out.println("❌ Invalid choice.");
            }
        }
    }

    private static void recordPayment() {
        System.out.println("\n── Record Payment ────────────────────────");
        System.out.print("Enter Booking ID: ");
        String bookingId = scanner.nextLine().trim();
        Booking booking = bookingService.getById(bookingId);

        if (booking == null) {
            System.out.println("❌ Booking not found.");
            return;
        }

        double amount = booking.getCylinder().getPrice();
        System.out.println("Amount to pay: ₹" + String.format("%.2f", amount));

        System.out.println("Select Payment Mode:");
        System.out.println("  1. Cash");
        System.out.println("  2. UPI");
        System.out.println("  3. Card");
        System.out.println("  4. Net Banking");
        int modeChoice = readIntInput("Select mode: ");

        PaymentMode mode;
        switch (modeChoice) {
            case 1:
                mode = PaymentMode.CASH;
                break;
            case 2:
                mode = PaymentMode.UPI;
                break;
            case 3:
                mode = PaymentMode.CARD;
                break;
            case 4:
                mode = PaymentMode.NETBANKING;
                break;
            default:
                System.out.println("❌ Invalid choice. Defaulting to Cash.");
                mode = PaymentMode.CASH;
        }

        String paymentId = IDGenerator.generatePaymentId();
        Payment payment = new Payment(paymentId, booking, amount, mode);
        paymentService.add(payment);
        payment.displayDetails();
    }

    private static void viewAllPayments() {
        List<Payment> payments = paymentService.getAll();
        if (payments.isEmpty()) {
            System.out.println("\n📭 No payments recorded yet.");
            return;
        }
        System.out.println("\n── All Payments (" + payments.size() + ") ──────────────────");
        System.out.printf("%-12s %-12s %-18s %-10s %-12s %-10s%n",
                "Payment ID", "Booking ID", "Customer", "Amount", "Mode", "Status");
        System.out.println(repeatChar('─', 74));
        for (Payment p : payments) {
            System.out.printf("%-12s %-12s %-18s ₹%-9.2f %-12s %-10s%n",
                    p.getPaymentId(), p.getBooking().getBookingId(),
                    p.getBooking().getCustomer().getName(),
                    p.getAmount(), p.getPaymentMode(), p.getPaymentStatus());
        }
    }

    private static void viewPaymentById() {
        System.out.print("\nEnter Payment ID: ");
        String id = scanner.nextLine().trim();
        Payment payment = paymentService.getById(id);
        if (payment != null) {
            payment.displayDetails();
        } else {
            System.out.println("❌ Payment not found with ID: " + id);
        }
    }

    private static void viewPaymentsByBooking() {
        System.out.print("\nEnter Booking ID: ");
        String bookingId = scanner.nextLine().trim();
        List<Payment> payments = paymentService.getByBookingId(bookingId);
        if (payments.isEmpty()) {
            System.out.println("📭 No payments found for booking: " + bookingId);
        } else {
            for (Payment p : payments) {
                p.displayDetails();
            }
        }
    }

    private static void refundPayment() {
        System.out.print("\nEnter Payment ID to refund: ");
        String id = scanner.nextLine().trim();
        paymentService.delete(id);
    }

    // ──────────────────── COMPLAINT MANAGEMENT ──────────────────────────

    private static void complaintManagementMenu() {
        boolean back = false;
        while (!back) {
            System.out.println();
            System.out.println("┌──────────────────────────────────────────┐");
            System.out.println("│       📝 COMPLAINT MANAGEMENT            │");
            System.out.println("├──────────────────────────────────────────┤");
            System.out.println("│  1. File New Complaint                   │");
            System.out.println("│  2. View All Complaints                  │");
            System.out.println("│  3. View Complaint Details (by ID)       │");
            System.out.println("│  4. Mark Complaint In Progress           │");
            System.out.println("│  5. Resolve Complaint                    │");
            System.out.println("│  6. View Complaints by Customer          │");
            System.out.println("│  0. ← Back to Main Menu                 │");
            System.out.println("└──────────────────────────────────────────┘");

            int choice = readIntInput("Enter your choice: ");
            switch (choice) {
                case 1:
                    fileComplaint();
                    break;
                case 2:
                    viewAllComplaints();
                    break;
                case 3:
                    viewComplaintById();
                    break;
                case 4:
                    markComplaintInProgress();
                    break;
                case 5:
                    resolveComplaint();
                    break;
                case 6:
                    viewComplaintsByCustomer();
                    break;
                case 0:
                    back = true;
                    break;
                default:
                    System.out.println("❌ Invalid choice.");
            }
        }
    }

    private static void fileComplaint() {
        System.out.println("\n── File New Complaint ─────────────────────");
        System.out.print("Enter Customer ID: ");
        String custId = scanner.nextLine().trim();
        Customer customer = customerService.getById(custId);

        if (customer == null) {
            System.out.println("❌ Customer not found.");
            return;
        }

        System.out.print("Describe the complaint: ");
        String description = scanner.nextLine().trim();
        if (description.isEmpty()) {
            System.out.println("❌ Complaint description cannot be empty.");
            return;
        }

        String complaintId = IDGenerator.generateComplaintId();
        Complaint complaint = new Complaint(complaintId, customer, description);
        complaintService.add(complaint);
        complaint.displayDetails();
    }

    private static void viewAllComplaints() {
        List<Complaint> complaints = complaintService.getAll();
        if (complaints.isEmpty()) {
            System.out.println("\n📭 No complaints filed yet.");
            return;
        }
        System.out.println("\n── All Complaints (" + complaints.size() + ") ──────────────");
        System.out.printf("%-12s %-18s %-28s %-14s%n",
                "Complaint ID", "Customer", "Description", "Status");
        System.out.println(repeatChar('─', 72));
        for (Complaint c : complaints) {
            String desc = c.getDescription().length() > 26
                    ? c.getDescription().substring(0, 26) + ".."
                    : c.getDescription();
            System.out.printf("%-12s %-18s %-28s %-14s%n",
                    c.getComplaintId(), c.getCustomer().getName(), desc, c.getStatus());
        }
    }

    private static void viewComplaintById() {
        System.out.print("\nEnter Complaint ID: ");
        String id = scanner.nextLine().trim();
        Complaint complaint = complaintService.getById(id);
        if (complaint != null) {
            complaint.displayDetails();
        } else {
            System.out.println("❌ Complaint not found with ID: " + id);
        }
    }

    private static void markComplaintInProgress() {
        System.out.print("\nEnter Complaint ID: ");
        String id = scanner.nextLine().trim();
        complaintService.markInProgress(id);
    }

    private static void resolveComplaint() {
        System.out.print("\nEnter Complaint ID to resolve: ");
        String id = scanner.nextLine().trim();
        complaintService.resolveComplaint(id);
    }

    private static void viewComplaintsByCustomer() {
        System.out.print("\nEnter Customer ID: ");
        String custId = scanner.nextLine().trim();
        List<Complaint> complaints = complaintService.getByCustomerId(custId);
        if (complaints.isEmpty()) {
            System.out.println("📭 No complaints found for customer: " + custId);
        } else {
            System.out.println("\n── Complaints for " + custId + " ──────────────");
            for (Complaint c : complaints) {
                System.out.println("  " + c);
            }
        }
    }

    // ──────────────────── REPORTS & DASHBOARD ───────────────────────────

    private static void reportsAndDashboard() {
        boolean back = false;
        while (!back) {
            System.out.println();
            System.out.println("┌──────────────────────────────────────────┐");
            System.out.println("│       📊 REPORTS & DASHBOARD             │");
            System.out.println("├──────────────────────────────────────────┤");
            System.out.println("│  1. Dashboard Summary                    │");
            System.out.println("│  2. Customer Report                      │");
            System.out.println("│  3. Booking Report                       │");
            System.out.println("│  4. Payment Report                       │");
            System.out.println("│  5. Complaint Report                     │");
            System.out.println("│  0. ← Back to Main Menu                 │");
            System.out.println("└──────────────────────────────────────────┘");

            int choice = readIntInput("Enter your choice: ");
            switch (choice) {
                case 1:
                    showDashboard();
                    break;
                case 2:
                    System.out.println(customerService.generateReport());
                    break;
                case 3:
                    System.out.println(bookingService.generateReport());
                    break;
                case 4:
                    System.out.println(paymentService.generateReport());
                    break;
                case 5:
                    System.out.println(complaintService.generateReport());
                    break;
                case 0:
                    back = true;
                    break;
                default:
                    System.out.println("❌ Invalid choice.");
            }
        }
    }

    private static void showDashboard() {
        System.out.println();
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║          🏠 BHARAT GAS AGENCY — DASHBOARD               ║");
        System.out.println("╠══════════════════════════════════════════════════════════╣");
        System.out.printf("║  Total Customers     : %-33d║%n", customerService.getAll().size());
        System.out.printf("║  Active Connections  : %-33d║%n",
                customerService.getByStatus(ConnectionStatus.ACTIVE).size());
        System.out.printf("║  Total Bookings      : %-33d║%n", bookingService.getAll().size());
        System.out.printf("║  Pending Deliveries  : %-33d║%n",
                bookingService.getByStatus(BookingStatus.PENDING).size() +
                        bookingService.getByStatus(BookingStatus.CONFIRMED).size() +
                        bookingService.getByStatus(BookingStatus.OUT_FOR_DELIVERY).size());
        System.out.printf("║  Completed Deliveries: %-33d║%n",
                bookingService.getByStatus(BookingStatus.DELIVERED).size());
        System.out.printf("║  Total Revenue       : ₹%-32.2f║%n", paymentService.getTotalRevenue());
        System.out.printf("║  Open Complaints     : %-33d║%n",
                complaintService.getByStatus(ComplaintStatus.OPEN).size() +
                        complaintService.getByStatus(ComplaintStatus.IN_PROGRESS).size());
        System.out.println("╚══════════════════════════════════════════════════════════╝");
    }

    // ──────────────────── UTILITY / HELPERS ─────────────────────────────

    private static int readIntInput(String prompt) {
        System.out.print(prompt);
        try {
            String line = scanner.nextLine().trim();
            return Integer.parseInt(line);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static String repeatChar(char c, int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++)
            sb.append(c);
        return sb.toString();
    }

    private static String readValidatedInput(String prompt, String type) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            switch (type) {
                case "name":
                    if (InputValidator.isValidName(input))
                        return input;
                    System.out.println("  ⚠️  Invalid name. Use only letters and spaces (2-50 chars).");
                    break;
                case "phone":
                    if (InputValidator.isValidPhone(input))
                        return input;
                    System.out.println("  ⚠️  Invalid phone. Enter 10-digit Indian mobile number.");
                    break;
                case "email":
                    if (InputValidator.isValidEmail(input))
                        return input;
                    System.out.println("  ⚠️  Invalid email format.");
                    break;
                default:
                    if (InputValidator.isNotEmpty(input))
                        return input;
                    System.out.println("  ⚠️  Input cannot be empty.");
            }
        }
    }

    // ──────────────────── SAMPLE DATA ───────────────────────────────────

    /**
     * Loads sample data for demonstration purposes.
     * Demonstrates: Object creation, composition, and service usage.
     */
    private static void loadSampleData() {
        // Sample Customers
        Customer c1 = new Customer(IDGenerator.generateCustomerId(), "Rajesh Kumar",
                "9876543210", "12, MG Road, Pune", "rajesh.kumar@email.com", ConnectionType.DOMESTIC);
        Customer c2 = new Customer(IDGenerator.generateCustomerId(), "Priya Sharma",
                "9123456789", "45, Station Road, Mumbai", "priya.sharma@email.com", ConnectionType.DOMESTIC);
        Customer c3 = new Customer(IDGenerator.generateCustomerId(), "Hotel Grand Palace",
                "9988776655", "78, Commercial St, Delhi", "info@grandpalace.com", ConnectionType.COMMERCIAL);

        customerService.add(c1);
        customerService.add(c2);
        customerService.add(c3);

        // Sample Bookings
        Booking b1 = new Booking(IDGenerator.generateBookingId(), c1, Cylinder.domestic14Kg());
        Booking b2 = new Booking(IDGenerator.generateBookingId(), c2, Cylinder.smallCylinder5Kg());
        Booking b3 = new Booking(IDGenerator.generateBookingId(), c3, Cylinder.freeTradeCommercial19Kg());

        bookingService.add(b1);
        bookingService.add(b2);
        bookingService.add(b3);
        c1.addBooking(b1);
        c2.addBooking(b2);
        c3.addBooking(b3);

        // Mark one as delivered
        bookingService.updateDeliveryStatus(b1.getBookingId(), BookingStatus.DELIVERED);

        // Sample Payment
        Payment p1 = new Payment(IDGenerator.generatePaymentId(), b1, b1.getCylinder().getPrice(), PaymentMode.UPI);
        paymentService.add(p1);

        // Sample Complaint
        Complaint cmp1 = new Complaint(IDGenerator.generateComplaintId(), c2, "Delayed delivery of cylinder");
        complaintService.add(cmp1);

        System.out.println("\n📦 Sample data loaded successfully!\n");
    }
}
