package com.bharatgas.crm.service;

import com.bharatgas.crm.model.Booking;
import com.bharatgas.crm.enums.BookingStatus;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for managing cylinder bookings.
 * Demonstrates: Interface implementation (CRUDOperations, Reportable).
 */
public class BookingService implements CRUDOperations<Booking>, Reportable {

    private final List<Booking> bookings;

    public BookingService() {
        this.bookings = new ArrayList<>();
    }

    @Override
    public void add(Booking booking) {
        bookings.add(booking);
        System.out.println("✅ Booking created successfully: " + booking.getBookingId());
        System.out.println("   Expected Delivery: " + booking.getExpectedDeliveryDate());
    }

    @Override
    public void update(String id, Booking updatedBooking) {
        for (int i = 0; i < bookings.size(); i++) {
            if (bookings.get(i).getBookingId().equalsIgnoreCase(id)) {
                bookings.set(i, updatedBooking);
                System.out.println("✅ Booking updated successfully: " + id);
                return;
            }
        }
        System.out.println("❌ Booking not found with ID: " + id);
    }

    @Override
    public void delete(String id) {
        Booking booking = getById(id);
        if (booking != null) {
            booking.setStatus(BookingStatus.CANCELLED);
            System.out.println("✅ Booking cancelled: " + id);
        } else {
            System.out.println("❌ Booking not found with ID: " + id);
        }
    }

    @Override
    public Booking getById(String id) {
        return bookings.stream()
                .filter(b -> b.getBookingId().equalsIgnoreCase(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Booking> getAll() {
        return new ArrayList<>(bookings);
    }

    /**
     * Get bookings for a specific customer.
     */
    public List<Booking> getByCustomerId(String customerId) {
        return bookings.stream()
                .filter(b -> b.getCustomer().getCustomerId().equalsIgnoreCase(customerId))
                .collect(Collectors.toList());
    }

    /**
     * Get bookings filtered by status.
     */
    public List<Booking> getByStatus(BookingStatus status) {
        return bookings.stream()
                .filter(b -> b.getStatus() == status)
                .collect(Collectors.toList());
    }

    /**
     * Update the delivery status of a booking.
     */
    public void updateDeliveryStatus(String bookingId, BookingStatus newStatus) {
        Booking booking = getById(bookingId);
        if (booking != null) {
            booking.setStatus(newStatus);
            if (newStatus == BookingStatus.DELIVERED) {
                booking.setActualDeliveryDate(LocalDate.now());
            }
            System.out.println("✅ Booking " + bookingId + " status updated to: " + newStatus.getDisplayName());
        } else {
            System.out.println("❌ Booking not found with ID: " + bookingId);
        }
    }

    @Override
    public String generateReport() {
        long totalBookings = bookings.size();
        long pending = bookings.stream().filter(b -> b.getStatus() == BookingStatus.PENDING).count();
        long confirmed = bookings.stream().filter(b -> b.getStatus() == BookingStatus.CONFIRMED).count();
        long outForDelivery = bookings.stream().filter(b -> b.getStatus() == BookingStatus.OUT_FOR_DELIVERY).count();
        long delivered = bookings.stream().filter(b -> b.getStatus() == BookingStatus.DELIVERED).count();
        long cancelled = bookings.stream().filter(b -> b.getStatus() == BookingStatus.CANCELLED).count();

        StringBuilder report = new StringBuilder();
        report.append("\n╔══════════════════════════════════════════════════════════╗\n");
        report.append("║            📊 BOOKING REPORT                            ║\n");
        report.append("╠══════════════════════════════════════════════════════════╣\n");
        report.append(String.format("║  Total Bookings      : %-33d║%n", totalBookings));
        report.append(String.format("║  Pending             : %-33d║%n", pending));
        report.append(String.format("║  Confirmed           : %-33d║%n", confirmed));
        report.append(String.format("║  Out for Delivery    : %-33d║%n", outForDelivery));
        report.append(String.format("║  Delivered           : %-33d║%n", delivered));
        report.append(String.format("║  Cancelled           : %-33d║%n", cancelled));
        report.append("╚══════════════════════════════════════════════════════════╝\n");

        return report.toString();
    }
}
