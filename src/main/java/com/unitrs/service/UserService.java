package com.unitrs.service;

import com.unitrs.model.entity.User;
import java.util.List;

public interface UserService {
    User authenticate(String identifierOrEmail, String password);
    boolean register(User user);
    User findById(int id);
    User findByIdentifier(String identifier);
    User findByEmail(String email);
    List<User> findUnverifiedStudents();
    boolean verifyStudent(int id, boolean isVerified);
    List<User> findAllUsers();
    List<User> findProfessors();
    boolean updateUserStatus(int id, boolean isActive);
    boolean createStaffUser(User user);
}
