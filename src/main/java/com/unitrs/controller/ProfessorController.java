package com.unitrs.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/professor/*")
public class ProfessorController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();

        if (path == null || path.equals("/") || path.equals("/dashboard")) {
            request.getRequestDispatcher("/WEB-INF/views/professor/dashboard.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/professor/dashboard");
        }
    }
}
