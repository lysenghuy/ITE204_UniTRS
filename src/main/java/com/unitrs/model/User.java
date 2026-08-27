package com.unitrs.model;

import java.sql.Timestamp;

public class User {

    private int id;
    private String userIdentifier;
    private String password;
    private String fullName;
    private String email;
    private Role role;
    private String major;
    private boolean isVerified;
    private boolean isActive;
    private Timestamp createdAt;

    public User() {}

    public User(int id, String userIdentifier, String password, String fullName, String email, 
                Role role, String major, boolean isVerified, boolean isActive, Timestamp createdAt) {
        this.id = id;
        this.userIdentifier = userIdentifier;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.role = role;
        this.major = major;
        this.isVerified = isVerified;
        this.isActive = isActive;
        this.createdAt = createdAt;
    }

    public User(String userIdentifier, String password, String fullName, String email, Role role, String major) {
        this.userIdentifier = userIdentifier;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.role = role;
        this.major = major;
        this.isActive = true;
        this.isVerified = (role != Role.STUDENT);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserIdentifier() {
        return userIdentifier;
    }

    public void setUserIdentifier(String userIdentifier) {
        this.userIdentifier = userIdentifier;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isStudent() {
        return role == Role.STUDENT;
    }

    public boolean isProfessor() {
        return role == Role.PROFESSOR;
    }

    public boolean isDean() {
        return role == Role.DEAN;
    }

    public boolean isAdmin() {
        return role == Role.ADMIN;
    }
}