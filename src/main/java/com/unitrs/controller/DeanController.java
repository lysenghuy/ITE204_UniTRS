package com.unitrs.controller;

import com.unitrs.exceptions.ValidationException;
import com.unitrs.model.entity.Course;
import com.unitrs.model.entity.Term;
import com.unitrs.repository.CourseRepository;
import com.unitrs.repository.TermRepository;
import com.unitrs.service.DeanService;
import com.unitrs.service.impl.DeanServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/dean/*")
public class DeanController extends HttpServlet {

    private DeanService deanService;

    @Override
    public void init() throws ServletException {
        this.deanService = new DeanServiceImpl(new CourseRepository(), new TermRepository());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();

        if (path == null || path.equals("/") || path.equals("/dashboard")) {
            showDashboard(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/dean/dashboard");
        }
    }

    private void showDashboard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Load data for the dashboard tabs
        List<Course> courses = deanService.getAllCourses();
        List<Term> terms = deanService.getAllTerms();
        Map<Term, List<Course>> curriculumMap = deanService.getTermCurriculumMap();

        request.setAttribute("courses", courses);
        request.setAttribute("terms", terms);
        request.setAttribute("curriculumMap", curriculumMap);

        // Retain the active tab state if provided (for returning after a form submission)
        String activeTab = request.getParameter("tab");
        if (activeTab == null) activeTab = "courses";
        request.setAttribute("activeTab", activeTab);

        request.getRequestDispatcher("/WEB-INF/views/dean/dashboard.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String activeTab = "courses";

        try {
            if ("addCourse".equals(action)) {
                activeTab = "courses";
                String code = request.getParameter("courseCode");
                String title = request.getParameter("courseTitle");
                int credits = Integer.parseInt(request.getParameter("credits"));
                deanService.addCourse(code, title, credits);
                request.setAttribute("successMessage", "Course successfully added.");

            } else if ("updateCourse".equals(action)) {
                activeTab = "courses";
                int id = Integer.parseInt(request.getParameter("courseId"));
                String code = request.getParameter("courseCode");
                String title = request.getParameter("courseTitle");
                int credits = Integer.parseInt(request.getParameter("credits"));
                deanService.updateCourse(id, code, title, credits);
                request.setAttribute("successMessage", "Course successfully updated.");

            } else if ("addTerm".equals(action)) {
                activeTab = "terms";
                int termNumber = Integer.parseInt(request.getParameter("termNumber"));
                String termName = request.getParameter("termName");
                deanService.addTerm(termNumber, termName);
                request.setAttribute("successMessage", "Term successfully added.");

            } else if ("updateTerm".equals(action)) {
                activeTab = "terms";
                int id = Integer.parseInt(request.getParameter("termId"));
                int termNumber = Integer.parseInt(request.getParameter("termNumber"));
                String termName = request.getParameter("termName");
                deanService.updateTerm(id, termNumber, termName);
                request.setAttribute("successMessage", "Term successfully updated.");

            } else if ("bundleCourse".equals(action)) {
                activeTab = "bundles";
                int termId = Integer.parseInt(request.getParameter("termId"));
                int courseId = Integer.parseInt(request.getParameter("courseId"));
                deanService.assignCourseToTerm(termId, courseId);
                request.setAttribute("successMessage", "Course bundled to Term successfully.");

            } else if ("unbundleCourse".equals(action)) {
                activeTab = "bundles";
                int termId = Integer.parseInt(request.getParameter("termId"));
                int courseId = Integer.parseInt(request.getParameter("courseId"));
                deanService.removeCourseFromTerm(termId, courseId);
                request.setAttribute("successMessage", "Course removed from Term.");
            }

        } catch (ValidationException | NumberFormatException e) {
            String msg = (e instanceof NumberFormatException) ? "Invalid number format." : e.getMessage();
            request.setAttribute("errorMessage", msg);
        } catch (Exception e) {
            request.setAttribute("errorMessage", "An unexpected error occurred: " + e.getMessage());
        }

        // Instead of redirecting, forward to doGet to show the dashboard with the message and active tab
        request.setAttribute("tab", activeTab);
        showDashboard(request, response);
    }
}
