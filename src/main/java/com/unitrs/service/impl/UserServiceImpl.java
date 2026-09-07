package com.unitrs.service.impl;

import com.unitrs.model.entity.User;
import com.unitrs.repository.UserDAO;
import com.unitrs.service.UserService;
import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    public UserServiceImpl() {
        this.userDAO = new UserDAO();
    }

    @Override
    public User authenticate(String identifierOrEmail, String password) {
        return userDAO.authenticate(identifierOrEmail, password);
    }

    @Override
    public boolean register(User user) {
        return userDAO.register(user);
    }

    @Override
    public User findById(int id) {
        return userDAO.findById(id);
    }

    @Override
    public User findByIdentifier(String identifier) {
        return userDAO.findByIdentifier(identifier);
    }

    @Override
    public User findByEmail(String email) {
        return userDAO.findByEmail(email);
    }

    @Override
    public List<User> findUnverifiedStudents() {
        return userDAO.findUnverifiedStudents();
    }

    @Override
    public boolean verifyStudent(int id, boolean isVerified) {
        return userDAO.verifyStudent(id, isVerified);
    }

    @Override
    public List<User> findAllUsers() {
        return userDAO.findAllUsers();
    }

    @Override
    public List<User> findProfessors() {
        return userDAO.findProfessors();
    }

    @Override
    public boolean updateUserStatus(int id, boolean isActive) {
        return userDAO.updateUserStatus(id, isActive);
    }

    @Override
    public boolean createStaffUser(User user) {
        return userDAO.createStaffUser(user);
    }
}
