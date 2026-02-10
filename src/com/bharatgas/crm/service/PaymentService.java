package com.bharatgas.crm.service;

import com.bharatgas.crm.model.Payment;
import com.bharatgas.crm.enums.PaymentMode;
import com.bharatgas.crm.enums.PaymentStatus;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for managing payments.
 * Demonstrates: Interface implementation (CRUDOperations, Reportable).
 */
public class PaymentService implements CRUDOperations<Payment>, Reportable {

    private final List<Payment> payments;

    public PaymentService() {
        this.payments = new ArrayList<>();
    }

    @Override
    public void add(Payment payment) {
        payments.add(payment);
        System.out.println("✅ Payment recorded successfully: " + payment.getPaymentId());
    }

    @Override
    public void update(String id, Payment updatedPayment) {
        for (int i = 0; i < payments.size(); i++) {
            if (payments.get(i).getPaymentId().equalsIgnoreCase(id)) {
                payments.set(i, updatedPayment);
                System.out.println("✅ Payment updated: " + id);
                return;
            }
        }
        System.out.println("❌ Payment not found with ID: " + id);
    }

    @Override
    public void delete(String id) {
        Payment payment = getById(id);
        if (payment != null) {
            payment.setPaymentStatus(PaymentStatus.REFUNDED);
            System.out.println("✅ Payment refunded: " + id);
        } else {
            System.out.println("❌ Payment not found with ID: " + id);
        }
    }

    @Override
    public Payment getById(String id) {
        return payments.stream()
                .filter(p -> p.getPaymentId().equalsIgnoreCase(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Payment> getAll() {
        return new ArrayList<>(payments);
    }

    /**
     * Get payments for a specific booking.
     */
    public List<Payment> getByBookingId(String bookingId) {
        return payments.stream()
                .filter(p -> p.getBooking().getBookingId().equalsIgnoreCase(bookingId))
                .collect(Collectors.toList());
    }

    /**
     * Get payments by payment mode.
     */
    public List<Payment> getByPaymentMode(PaymentMode mode) {
        return payments.stream()
                .filter(p -> p.getPaymentMode() == mode)
                .collect(Collectors.toList());
    }

    /**
     * Calculate total revenue collected.
     */
    public double getTotalRevenue() {
        return payments.stream()
                .filter(p -> p.getPaymentStatus() == PaymentStatus.COMPLETED)
                .mapToDouble(Payment::getAmount)
                .sum();
    }

    @Override
    public String generateReport() {
        long totalPayments = payments.size();
        long completed = payments.stream()
                .filter(p -> p.getPaymentStatus() == PaymentStatus.COMPLETED).count();
        long pending = payments.stream()
                .filter(p -> p.getPaymentStatus() == PaymentStatus.PENDING).count();
        long refunded = payments.stream()
                .filter(p -> p.getPaymentStatus() == PaymentStatus.REFUNDED).count();
        double totalRevenue = getTotalRevenue();

        long cashPayments = payments.stream().filter(p -> p.getPaymentMode() == PaymentMode.CASH).count();
        long upiPayments = payments.stream().filter(p -> p.getPaymentMode() == PaymentMode.UPI).count();
        long cardPayments = payments.stream().filter(p -> p.getPaymentMode() == PaymentMode.CARD).count();
        long netBankingPayments = payments.stream().filter(p -> p.getPaymentMode() == PaymentMode.NETBANKING).count();

        StringBuilder report = new StringBuilder();
        report.append("\n╔══════════════════════════════════════════════════════════╗\n");
        report.append("║            📊 PAYMENT REPORT                            ║\n");
        report.append("╠══════════════════════════════════════════════════════════╣\n");
        report.append(String.format("║  Total Payments    : %-35d║%n", totalPayments));
        report.append(String.format("║  Completed         : %-35d║%n", completed));
        report.append(String.format("║  Pending           : %-35d║%n", pending));
        report.append(String.format("║  Refunded          : %-35d║%n", refunded));
        report.append(String.format("║  Total Revenue     : ₹%-34.2f║%n", totalRevenue));
        report.append("╠══════════════════════════════════════════════════════════╣\n");
        report.append("║  Payment Mode Breakdown:                                ║\n");
        report.append(String.format("║    Cash            : %-35d║%n", cashPayments));
        report.append(String.format("║    UPI             : %-35d║%n", upiPayments));
        report.append(String.format("║    Card            : %-35d║%n", cardPayments));
        report.append(String.format("║    Net Banking     : %-35d║%n", netBankingPayments));
        report.append("╚══════════════════════════════════════════════════════════╝\n");

        return report.toString();
    }
}
