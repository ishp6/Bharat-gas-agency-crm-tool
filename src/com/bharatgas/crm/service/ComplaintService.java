package com.bharatgas.crm.service;

import com.bharatgas.crm.model.Complaint;
import com.bharatgas.crm.enums.ComplaintStatus;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for managing customer complaints.
 * Demonstrates: Interface implementation (CRUDOperations, Reportable).
 */
public class ComplaintService implements CRUDOperations<Complaint>, Reportable {

    private final List<Complaint> complaints;

    public ComplaintService() {
        this.complaints = new ArrayList<>();
    }

    @Override
    public void add(Complaint complaint) {
        complaints.add(complaint);
        System.out.println("✅ Complaint registered: " + complaint.getComplaintId());
    }

    @Override
    public void update(String id, Complaint updatedComplaint) {
        for (int i = 0; i < complaints.size(); i++) {
            if (complaints.get(i).getComplaintId().equalsIgnoreCase(id)) {
                complaints.set(i, updatedComplaint);
                System.out.println("✅ Complaint updated: " + id);
                return;
            }
        }
        System.out.println("❌ Complaint not found with ID: " + id);
    }

    @Override
    public void delete(String id) {
        Complaint complaint = getById(id);
        if (complaint != null) {
            complaint.setStatus(ComplaintStatus.CLOSED);
            System.out.println("✅ Complaint closed: " + id);
        } else {
            System.out.println("❌ Complaint not found with ID: " + id);
        }
    }

    @Override
    public Complaint getById(String id) {
        return complaints.stream()
                .filter(c -> c.getComplaintId().equalsIgnoreCase(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Complaint> getAll() {
        return new ArrayList<>(complaints);
    }

    /**
     * Get complaints for a specific customer.
     */
    public List<Complaint> getByCustomerId(String customerId) {
        return complaints.stream()
                .filter(c -> c.getCustomer().getCustomerId().equalsIgnoreCase(customerId))
                .collect(Collectors.toList());
    }

    /**
     * Get complaints filtered by status.
     */
    public List<Complaint> getByStatus(ComplaintStatus status) {
        return complaints.stream()
                .filter(c -> c.getStatus() == status)
                .collect(Collectors.toList());
    }

    /**
     * Resolve a complaint by ID.
     */
    public void resolveComplaint(String complaintId) {
        Complaint complaint = getById(complaintId);
        if (complaint != null) {
            complaint.setStatus(ComplaintStatus.RESOLVED);
            complaint.setResolvedDate(LocalDate.now());
            System.out.println("✅ Complaint resolved: " + complaintId);
        } else {
            System.out.println("❌ Complaint not found with ID: " + complaintId);
        }
    }

    /**
     * Mark a complaint as in progress.
     */
    public void markInProgress(String complaintId) {
        Complaint complaint = getById(complaintId);
        if (complaint != null) {
            complaint.setStatus(ComplaintStatus.IN_PROGRESS);
            System.out.println("✅ Complaint marked in progress: " + complaintId);
        } else {
            System.out.println("❌ Complaint not found with ID: " + complaintId);
        }
    }

    @Override
    public String generateReport() {
        long totalComplaints = complaints.size();
        long open = complaints.stream().filter(c -> c.getStatus() == ComplaintStatus.OPEN).count();
        long inProgress = complaints.stream().filter(c -> c.getStatus() == ComplaintStatus.IN_PROGRESS).count();
        long resolved = complaints.stream().filter(c -> c.getStatus() == ComplaintStatus.RESOLVED).count();
        long closed = complaints.stream().filter(c -> c.getStatus() == ComplaintStatus.CLOSED).count();

        StringBuilder report = new StringBuilder();
        report.append("\n╔══════════════════════════════════════════════════════════╗\n");
        report.append("║            📊 COMPLAINT REPORT                          ║\n");
        report.append("╠══════════════════════════════════════════════════════════╣\n");
        report.append(String.format("║  Total Complaints  : %-35d║%n", totalComplaints));
        report.append(String.format("║  Open              : %-35d║%n", open));
        report.append(String.format("║  In Progress       : %-35d║%n", inProgress));
        report.append(String.format("║  Resolved          : %-35d║%n", resolved));
        report.append(String.format("║  Closed            : %-35d║%n", closed));
        report.append("╚══════════════════════════════════════════════════════════╝\n");

        return report.toString();
    }
}
