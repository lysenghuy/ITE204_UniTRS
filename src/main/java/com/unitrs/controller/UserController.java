package com.unitrs.controller;

import com.unitrs.model.entity.User;
import com.unitrs.repository.UserRepository;
import com.unitrs.service.UserService;
import com.unitrs.service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/users")
public class UserController extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        this.userService = new UserServiceImpl(new UserRepository());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> userList = userService.findAllUsers();
        request.setAttribute("users", userList);
        request.getRequestDispatcher("/WEB-INF/views/users.jsp").forward(request, response);
    }
}
