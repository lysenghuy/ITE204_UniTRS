package com.unitrs.filter;

import com.unitrs.exceptions.UnauthorizedException;
import com.unitrs.exceptions.UserNotFoundException;
import com.unitrs.exceptions.ValidationException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebFilter("/*")
public class GlobalExceptionFilter implements Filter {

    private static final Logger LOGGER = Logger.getLogger(GlobalExceptionFilter.class.getName());

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        try {
            chain.doFilter(request, response);
        } catch (UserNotFoundException | UnauthorizedException | ValidationException e) {
            LOGGER.log(Level.WARNING, "Business exception at " + httpRequest.getRequestURI(), e);
            httpRequest.setAttribute("errorMessage", e.getMessage());
            httpRequest.getRequestDispatcher("/error.jsp").forward(request, response);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Unhandled server error at " + httpRequest.getRequestURI(), e);
            httpRequest.setAttribute("errorMessage", "An unexpected internal server error occurred.");
            httpRequest.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}
