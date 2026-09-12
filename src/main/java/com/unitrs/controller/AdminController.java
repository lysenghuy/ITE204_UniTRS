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

@WebServlet("/admin/*")
public class AdminController extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        this.userService = new UserServiceImpl(new UserRepository());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getPathInfo();

        if (path == null || "/dashboard".equals(path)) {
            showDashboard(request, response);
        } else if ("/users".equals(path)) {
            showUsers(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/admin/dashboard");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getPathInfo();

        if ("/users/verify".equals(path)) {
            handleVerifyStudent(request, response);
        } else if ("/users/status".equals(path)) {
            handleUpdateStatus(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/admin/dashboard");
        }
    }

    private void showDashboard(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<User> allUsers = userService.findAllUsers();
        List<User> unverified = userService.findUnverifiedUsers();

        request.setAttribute("totalUsers", allUsers.size());
        request.setAttribute("pendingVerifications", unverified.size());

        long studentCount = allUsers.stream()
                .filter(u -> u.getRole() != null && u.getRole().name().equals("STUDENT"))
                .count();
        long staffCount = allUsers.size() - studentCount;

        request.setAttribute("studentCount", studentCount);
        request.setAttribute("staffCount", staffCount);

        request.getRequestDispatcher("/WEB-INF/views/admin/dashboard.jsp").forward(request, response);
    }

    private void showUsers(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<User> userList = userService.findAllUsers();
        List<User> unverifiedList = userService.findUnverifiedUsers();

        request.setAttribute("users", userList);
        request.setAttribute("unverifiedStudents", unverifiedList);

        request.getRequestDispatcher("/WEB-INF/views/admin/users.jsp").forward(request, response);
    }

    private void handleVerifyStudent(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        int userId = Integer.parseInt(request.getParameter("userId"));
        String action = request.getParameter("action");
        String role = request.getParameter("role");

        boolean isApproved = "approve".equals(action);
        userService.processUserVerification(userId, isApproved, role);

        response.sendRedirect(request.getContextPath() + "/admin/users");
    }

    private void handleUpdateStatus(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        int userId = Integer.parseInt(request.getParameter("userId"));
        String action = request.getParameter("action");

        boolean isActive = "activate".equals(action);
        userService.updateUserStatus(userId, isActive);

        response.sendRedirect(request.getContextPath() + "/admin/users");
    }
}
