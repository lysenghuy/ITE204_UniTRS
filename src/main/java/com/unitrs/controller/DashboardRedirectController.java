package com.unitrs.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet({"/professor/*", "/student/*"})
public class DashboardRedirectController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String uri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String path = uri.substring(contextPath.length());

        if (path.startsWith("/professor/")) {
            request.getRequestDispatcher("/WEB-INF/views/professor/dashboard.jsp").forward(request, response);
        } else if (path.startsWith("/student/")) {
            request.getRequestDispatcher("/WEB-INF/views/student/dashboard.jsp").forward(request, response);
        } else {
            response.sendRedirect(contextPath + "/auth/login");
        }
    }
}
