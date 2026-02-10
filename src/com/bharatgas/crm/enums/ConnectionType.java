package com.bharatgas.crm.enums;

public enum ConnectionType {
    DOMESTIC("Domestic"),
    COMMERCIAL("Commercial");

    private final String displayName;

    ConnectionType(String displayName) {
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
