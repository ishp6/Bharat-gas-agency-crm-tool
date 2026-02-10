package com.bharatgas.crm.model;

/**
 * Represents a gas cylinder (14.2 kg, 5 kg, or 19 kg variants).
 * Demonstrates: Encapsulation with private fields and public accessors.
 */
public class Cylinder {
    private String cylinderType; // e.g., "14.2 KG", "5 KG", "19 KG"
    private double weight;
    private double price;

    public Cylinder(String cylinderType, double weight, double price) {
        this.cylinderType = cylinderType;
        this.weight = weight;
        this.price = price;
    }

    // Pre-defined cylinder types (Factory-style static methods)
    public static Cylinder domestic14Kg() {
        return new Cylinder("14.2 KG Domestic", 14.2, 903.00);
    }

    public static Cylinder freeTradeCommercial19Kg() {
        return new Cylinder("19 KG Commercial", 19.0, 1850.00);
    }

    public static Cylinder smallCylinder5Kg() {
        return new Cylinder("5 KG Domestic", 5.0, 349.00);
    }

    // Getters and Setters
    public String getCylinderType() {
        return cylinderType;
    }

    public void setCylinderType(String cylinderType) {
        this.cylinderType = cylinderType;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("%s — ₹%.2f", cylinderType, price);
    }
}
