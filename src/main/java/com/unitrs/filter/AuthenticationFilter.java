package com.unitrs.filter;

import com.unitrs.model.entity.Role;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String uri = httpRequest.getRequestURI();
        String contextPath = httpRequest.getContextPath();
        String path = uri.substring(contextPath.length());

        HttpSession session = httpRequest.getSession(false);
        boolean isLoggedIn = (session != null && session.getAttribute("user") != null);

        // Smart Routing: If user is already logged in, redirect them to their dashboard
        // if they try to access the landing page or auth pages.
        if (isLoggedIn && (path.equals("/") || path.equals("/index.jsp") || path.equals("/auth/login") || path.equals("/auth/register"))) {
            com.unitrs.model.entity.User userObj = (com.unitrs.model.entity.User) session.getAttribute("user");
            Role userRole = (Role) session.getAttribute("role");
            
            if (userObj != null && userObj.getDeanSchoolId() != null) {
                httpResponse.sendRedirect(contextPath + "/dean/dashboard");
                return;
            } else if (userRole != null) {
                switch (userRole) {
                    case ADMIN:
                        httpResponse.sendRedirect(contextPath + "/admin/dashboard");
                        return;
                    case DEAN:
                        httpResponse.sendRedirect(contextPath + "/dean/dashboard");
                        return;
                    case PROFESSOR:
                        httpResponse.sendRedirect(contextPath + "/professor/dashboard");
                        return;
                    case STUDENT:
                        httpResponse.sendRedirect(contextPath + "/student/dashboard");
                        return;
                    default:
                        break;
                }
            }
        }

        // Allow these paths without login for guests
        if (path.startsWith("/auth/")
                || path.startsWith("/api/validate/")
                || path.startsWith("/static/")
                || path.equals("/error.jsp")
                || path.equals("/")
                || path.equals("/index.jsp")) {
            chain.doFilter(request, response);
            return;
        }

        // Check if user is logged in for protected paths
        if (!isLoggedIn) {
            httpResponse.sendRedirect(contextPath + "/auth/login");
            return;
        }

        // Check role-based access
        Role userRole = (Role) session.getAttribute("role");
        com.unitrs.model.entity.User userObj = (com.unitrs.model.entity.User) session.getAttribute("user");
        boolean isAssignedDean = userObj != null && userObj.getDeanSchoolId() != null;

        if (path.startsWith("/admin/") && userRole != Role.ADMIN) {
            httpRequest.setAttribute("errorMessage", "Access denied. Admin privileges required.");
            httpRequest.getRequestDispatcher("/error.jsp").forward(request, response);
            return;
        }
        if (path.startsWith("/dean/") && userRole != Role.DEAN && !isAssignedDean) {
            httpRequest.setAttribute("errorMessage", "Access denied. Dean privileges required.");
            httpRequest.getRequestDispatcher("/error.jsp").forward(request, response);
            return;
        }
        if (path.startsWith("/professor/") && userRole != Role.PROFESSOR && !isAssignedDean) {
            // Also allow Deans (who used to be professors) to access the professor dashboard
            httpRequest.setAttribute("errorMessage", "Access denied. Professor privileges required.");
            httpRequest.getRequestDispatcher("/error.jsp").forward(request, response);
            return;
        }
        if (path.startsWith("/student/") && userRole != Role.STUDENT) {
            httpRequest.setAttribute("errorMessage", "Access denied. Student privileges required.");
            httpRequest.getRequestDispatcher("/error.jsp").forward(request, response);
            return;
        }

        // All good, continue
        chain.doFilter(request, response);
    }
}
