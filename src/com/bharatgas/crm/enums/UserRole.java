package com.bharatgas.crm.enums;

public enum UserRole {
    ADMIN("Admin"),
    DELIVERY_PERSON("Delivery Person"),
    CUSTOMER_SUPPORT("Customer Support");

    private final String displayName;

    UserRole(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
