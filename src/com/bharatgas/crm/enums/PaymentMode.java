package com.bharatgas.crm.enums;

public enum PaymentMode {
    CASH("Cash"),
    UPI("UPI"),
    CARD("Card"),
    NETBANKING("Net Banking");

    private final String displayName;

    PaymentMode(String displayName) {
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
