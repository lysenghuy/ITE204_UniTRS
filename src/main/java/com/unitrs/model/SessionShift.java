package com.unitrs.model;

public enum SessionShift {
    MORNING("Morning", "08:00 AM - 11:15 AM"),
    AFTERNOON("Afternoon", "02:00 PM - 05:15 PM"),
    EVENING("Evening", "05:45 PM - 08:45 PM"),
    WEEKEND("Weekend", "Sat - Sun (08:00 AM - 04:30 PM)");

    private final String displayName;
    private final String timeRange;

    SessionShift(String displayName, String timeRange) {
        this.displayName = displayName;
        this.timeRange = timeRange;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getTimeRange() {
        return timeRange;
    }

    public static SessionShift fromString(String shiftStr) {
        if (shiftStr == null) {
            return null;
        }
        try {
            return SessionShift.valueOf(shiftStr.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}