package com.unitrs.controller;

import com.unitrs.model.entity.Role;
import com.unitrs.model.entity.User;
import com.unitrs.exceptions.UnauthorizedException;
import com.unitrs.exceptions.UserNotFoundException;
import com.unitrs.repository.UserRepository;
import com.unitrs.service.UserService;
import com.unitrs.service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/auth/*")
public class AuthController extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        this.userService = new UserServiceImpl(new UserRepository());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getPathInfo();

        if ("/logout".equals(path)) {
            handleLogout(request, response);
        } else if ("/register".equals(path)) {
            request.getRequestDispatcher("/WEB-INF/views/auth/register.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getPathInfo();

        if ("/login".equals(path)) {
            handleLogin(request, response);
        } else if ("/register".equals(path)) {
            handleRegister(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/auth/login");
        }
    }

    private void handleLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String identifierOrEmail = request.getParameter("identifier");
        String password = request.getParameter("password");

        // Basic validation
        if (identifierOrEmail == null || identifierOrEmail.trim().isEmpty()
                || password == null || password.trim().isEmpty()) {
            request.setAttribute("error", "Please enter both username and password.");
            request.setAttribute("identifier", identifierOrEmail);
            request.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(request, response);
            return;
        }

        try {
            User user = userService.authenticate(identifierOrEmail.trim(), password);

            // Check if user is verified
            if (!user.isVerified()) {
                request.setAttribute("error", "the account have not yet been verified yet wiat the gmail will sent to your gmail when your account have been verifed");
                request.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(request, response);
                return;
            }

            // Create session and store user info
            HttpSession session = request.getSession(true);
            session.setAttribute("user", user);
            session.setAttribute("role", user.getRole());
            session.setMaxInactiveInterval(30 * 60); // 30 minutes

            // Redirect based on role
            String contextPath = request.getContextPath();
            switch (user.getRole()) {
                case ADMIN:
                    response.sendRedirect(contextPath + "/admin/dashboard");
                    break;
                case DEAN:
                    response.sendRedirect(contextPath + "/dean/dashboard");
                    break;
                case PROFESSOR:
                    response.sendRedirect(contextPath + "/professor/dashboard");
                    break;
                case STUDENT:
                    response.sendRedirect(contextPath + "/student/dashboard");
                    break;
                default:
                    response.sendRedirect(contextPath + "/auth/login");
            }

        } catch (UserNotFoundException e) {
            request.setAttribute("error", "User not found or account is inactive.");
            request.setAttribute("identifier", identifierOrEmail);
            request.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(request, response);
        } catch (UnauthorizedException e) {
            request.setAttribute("error", "Invalid password. Please try again.");
            request.setAttribute("identifier", identifierOrEmail);
            request.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(request, response);
        }
    }

    private void handleRegister(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String identifier = request.getParameter("identifier");
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String major = request.getParameter("major");

        try {
            userService.registerNewUser(identifier, fullName, email, password, confirmPassword, major);
            request.setAttribute("success", "Registration successful! Your account is pending verification by an administrator.");
            request.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(request, response);
        } catch (com.unitrs.exceptions.ValidationException e) {
            request.setAttribute("error", e.getMessage());
            request.setAttribute("identifier", identifier);
            request.setAttribute("fullName", fullName);
            request.setAttribute("email", email);
            request.setAttribute("major", major);
            request.getRequestDispatcher("/WEB-INF/views/auth/register.jsp").forward(request, response);
        }
    }

    private void handleLogout(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        response.sendRedirect(request.getContextPath() + "/auth/login");
    }
}
