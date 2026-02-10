package com.bharatgas.crm.service;

import com.bharatgas.crm.model.Customer;
import com.bharatgas.crm.enums.ConnectionStatus;
import com.bharatgas.crm.enums.ConnectionType;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for managing customers.
 * Demonstrates: Interface implementation (CRUDOperations, Searchable,
 * Reportable),
 * Polymorphism (method implementations from interfaces).
 */
public class CustomerService implements CRUDOperations<Customer>, Searchable<Customer>, Reportable {

    private final List<Customer> customers;

    public CustomerService() {
        this.customers = new ArrayList<>();
    }

    @Override
    public void add(Customer customer) {
        customers.add(customer);
        System.out.println("✅ Customer registered successfully: " + customer.getCustomerId());
    }

    @Override
    public void update(String id, Customer updatedCustomer) {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getCustomerId().equalsIgnoreCase(id)) {
                customers.set(i, updatedCustomer);
                System.out.println("✅ Customer updated successfully: " + id);
                return;
            }
        }
        System.out.println("❌ Customer not found with ID: " + id);
    }

    @Override
    public void delete(String id) {
        Customer customer = getById(id);
        if (customer != null) {
            customer.setConnectionStatus(ConnectionStatus.INACTIVE);
            System.out.println("✅ Customer connection deactivated: " + id);
        } else {
            System.out.println("❌ Customer not found with ID: " + id);
        }
    }

    @Override
    public Customer getById(String id) {
        return customers.stream()
                .filter(c -> c.getCustomerId().equalsIgnoreCase(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Customer> getAll() {
        return new ArrayList<>(customers);
    }

    @Override
    public List<Customer> searchByName(String name) {
        return customers.stream()
                .filter(c -> c.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    /**
     * Get customers filtered by connection type.
     */
    public List<Customer> getByConnectionType(ConnectionType type) {
        return customers.stream()
                .filter(c -> c.getConnectionType() == type)
                .collect(Collectors.toList());
    }

    /**
     * Get customers filtered by connection status.
     */
    public List<Customer> getByStatus(ConnectionStatus status) {
        return customers.stream()
                .filter(c -> c.getConnectionStatus() == status)
                .collect(Collectors.toList());
    }

    @Override
    public String generateReport() {
        long totalCustomers = customers.size();
        long activeCustomers = customers.stream()
                .filter(c -> c.getConnectionStatus() == ConnectionStatus.ACTIVE).count();
        long inactiveCustomers = customers.stream()
                .filter(c -> c.getConnectionStatus() == ConnectionStatus.INACTIVE).count();
        long suspendedCustomers = customers.stream()
                .filter(c -> c.getConnectionStatus() == ConnectionStatus.SUSPENDED).count();
        long domesticConnections = customers.stream()
                .filter(c -> c.getConnectionType() == ConnectionType.DOMESTIC).count();
        long commercialConnections = customers.stream()
                .filter(c -> c.getConnectionType() == ConnectionType.COMMERCIAL).count();

        StringBuilder report = new StringBuilder();
        report.append("\n╔══════════════════════════════════════════════════════════╗\n");
        report.append("║            📊 CUSTOMER REPORT                           ║\n");
        report.append("╠══════════════════════════════════════════════════════════╣\n");
        report.append(String.format("║  Total Customers       : %-31d║%n", totalCustomers));
        report.append(String.format("║  Active Connections    : %-31d║%n", activeCustomers));
        report.append(String.format("║  Inactive Connections  : %-31d║%n", inactiveCustomers));
        report.append(String.format("║  Suspended Connections : %-31d║%n", suspendedCustomers));
        report.append("╠══════════════════════════════════════════════════════════╣\n");
        report.append(String.format("║  Domestic Connections  : %-31d║%n", domesticConnections));
        report.append(String.format("║  Commercial Connections: %-31d║%n", commercialConnections));
        report.append("╚══════════════════════════════════════════════════════════╝\n");

        return report.toString();
    }
}
