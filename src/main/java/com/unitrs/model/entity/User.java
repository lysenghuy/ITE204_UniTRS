package com.unitrs.model.entity;

import java.sql.Timestamp;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
}
