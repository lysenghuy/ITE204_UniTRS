package com.unitrs.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.unitrs.model.entity.User;
import com.unitrs.model.entity.ClassSection;
import com.unitrs.repository.ClassSectionRepository;
import com.unitrs.repository.UserRepository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/professor/*")
public class ProfessorController extends HttpServlet {
    
    private ClassSectionRepository classSectionRepository;
    private UserRepository userRepository;
    private com.unitrs.repository.AttendanceRepository attendanceRepository;
    private com.unitrs.repository.GradeRepository gradeRepository;

    @Override
    public void init() throws ServletException {
        this.classSectionRepository = new ClassSectionRepository();
        this.userRepository = new UserRepository();
        this.attendanceRepository = new com.unitrs.repository.AttendanceRepository();
        this.gradeRepository = new com.unitrs.repository.GradeRepository();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();
        String action = request.getParameter("action");

        if (path == null || path.equals("/") || path.equals("/dashboard")) {
            User user = (User) request.getSession().getAttribute("user");
            
            // Get all sections assigned to this professor
            List<ClassSection> sections = classSectionRepository.findByProfessorId(user.getId());
            
            // For each section, get the enrolled students and attendance records
            Map<ClassSection, List<User>> sectionStudentsMap = new LinkedHashMap<>();
            Map<Integer, List<com.unitrs.model.entity.AttendanceRecord>> sectionAttendanceMap = new LinkedHashMap<>();
            Map<Integer, List<com.unitrs.model.entity.Grade>> sectionGradesMap = new LinkedHashMap<>();
            
            for (ClassSection section : sections) {
                List<User> students = userRepository.findStudentsByClassSection(section.getId());
                sectionStudentsMap.put(section, students);
                
                List<com.unitrs.model.entity.AttendanceRecord> records = attendanceRepository.findRecordsByClassSectionId(section.getId());
                sectionAttendanceMap.put(section.getId(), records);
                
                List<com.unitrs.model.entity.Grade> grades = gradeRepository.findGradesByClassSectionId(section.getId());
                sectionGradesMap.put(section.getId(), grades);
            }
            
            request.setAttribute("sectionStudentsMap", sectionStudentsMap);
            request.setAttribute("sectionAttendanceMap", sectionAttendanceMap);
            request.setAttribute("sectionGradesMap", sectionGradesMap);
            
            // Success/Error messages from redirect
            if (request.getParameter("success") != null) {
                request.setAttribute("successMessage", "Attendance successfully saved!");
            }
            if (request.getParameter("error") != null) {
                request.setAttribute("errorMessage", "Failed to save attendance: " + request.getParameter("error"));
            }

            request.getRequestDispatcher("/WEB-INF/views/professor/dashboard.jsp").forward(request, response);
        } else if (path.equals("/attendance/view")) {
            // Future implementation for detailed single record view
            response.sendRedirect(request.getContextPath() + "/professor/dashboard");
        } else {
            response.sendRedirect(request.getContextPath() + "/professor/dashboard");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getPathInfo();

        if (path != null && path.equals("/attendance/save")) {
            try {
                int classSectionId = Integer.parseInt(request.getParameter("classSectionId"));
                String sessionDateStr = request.getParameter("sessionDate");
                java.sql.Date sessionDate = java.sql.Date.valueOf(sessionDateStr);
                
                // Get or create record
                com.unitrs.model.entity.AttendanceRecord record = attendanceRepository.findRecordBySectionAndDate(classSectionId, sessionDate);
                int recordId;
                if (record == null) {
                    recordId = attendanceRepository.createRecord(classSectionId, sessionDate);
                } else {
                    recordId = record.getId();
                }
                
                // Process student statuses
                List<User> students = userRepository.findStudentsByClassSection(classSectionId);
                for (User student : students) {
                    String status = request.getParameter("status_" + student.getId());
                    if (status != null && !status.isEmpty()) {
                        attendanceRepository.saveEntry(recordId, student.getId(), status);
                    }
                }
                
                response.sendRedirect(request.getContextPath() + "/professor/dashboard?success=1");
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect(request.getContextPath() + "/professor/dashboard?error=" + java.net.URLEncoder.encode(e.getMessage(), "UTF-8"));
            }
        } else if (path != null && path.equals("/grades/save")) {
            try {
                int classSectionId = Integer.parseInt(request.getParameter("classSectionId"));
                
                List<com.unitrs.model.entity.Grade> existingGrades = gradeRepository.findGradesByClassSectionId(classSectionId);
                
                for (com.unitrs.model.entity.Grade grade : existingGrades) {
                    int eid = grade.getEnrollmentId();
                    String attStr = request.getParameter("attendance_" + eid);
                    String assStr = request.getParameter("assignment_" + eid);
                    String midStr = request.getParameter("midterm_" + eid);
                    String finStr = request.getParameter("final_" + eid);
                    
                    if (attStr != null && assStr != null && midStr != null && finStr != null) {
                        double att = Double.parseDouble(attStr);
                        double ass = Double.parseDouble(assStr);
                        double mid = Double.parseDouble(midStr);
                        double fin = Double.parseDouble(finStr);
                        
                        double total = com.unitrs.utils.GradeCalculator.calculateTotal(att, ass, mid, fin);
                        String letter = com.unitrs.utils.GradeCalculator.calculateLetterGrade(total);
                        double gpa = com.unitrs.utils.GradeCalculator.calculateGpaPoint(letter);
                        
                        gradeRepository.saveGrade(eid, att, ass, mid, fin, total, letter, gpa);
                    }
                }
                response.sendRedirect(request.getContextPath() + "/professor/dashboard?success=1");
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect(request.getContextPath() + "/professor/dashboard?error=" + java.net.URLEncoder.encode(e.getMessage(), "UTF-8"));
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/professor/dashboard");
        }
    }
}
