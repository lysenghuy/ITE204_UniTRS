package com.unitrs.model;

public enum Role {
    STUDENT,
    PROFESSOR,
    DEAN,
    ADMIN;

    public static Role fromString(String roleStr) {
        if (roleStr == null) {
            return null;
        }
        try {
            return Role.valueOf(roleStr.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}