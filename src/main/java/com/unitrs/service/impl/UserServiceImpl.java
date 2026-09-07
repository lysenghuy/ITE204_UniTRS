package com.unitrs.service.impl;

import com.unitrs.model.entity.User;
import com.unitrs.utils.SecurityUtils;
import com.unitrs.exceptions.UnauthorizedException;
import com.unitrs.exceptions.UserNotFoundException;
import com.unitrs.exceptions.ValidationException;
import com.unitrs.repository.UserRepository;
import com.unitrs.service.UserService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userDAO;

    @Override
    public User authenticate(String identifierOrEmail, String password) {
        User user = userDAO.findByEmailOrIdentifier(identifierOrEmail);
        if (user == null) {
            throw new UserNotFoundException("User not found or inactive.");
        }
        if (!SecurityUtils.checkPassword(password, user.getPassword())) {
            throw new UnauthorizedException("Invalid password.");
        }
        return user;
    }

    @Override
    public boolean register(User user) {
        if (user.getPassword() == null || user.getPassword().length() < 6) {
            throw new ValidationException("Password must be at least 6 characters.");
        }
        user.setPassword(SecurityUtils.hashPassword(user.getPassword()));
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
