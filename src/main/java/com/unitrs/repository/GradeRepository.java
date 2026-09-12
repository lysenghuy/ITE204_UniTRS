package com.unitrs.repository;

import com.unitrs.model.entity.Grade;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class GradeRepository extends BaseRepository {

    public List<Grade> findGradesByClassSectionId(int classSectionId) {
        String sql = "SELECT " +
                     "e.id AS enrollment_id, " +
                     "e.student_id, " +
                     "u.user_identifier AS student_identifier, " +
                     "u.full_name AS student_name, " +
                     "COALESCE(g.attendance_score, 0.00) AS attendance_score, " +
                     "COALESCE(g.assignment_score, 0.00) AS assignment_score, " +
                     "COALESCE(g.midterm_score, 0.00) AS midterm_score, " +
                     "COALESCE(g.final_score, 0.00) AS final_score, " +
                     "COALESCE(g.total_score, 0.00) AS total_score, " +
                     "COALESCE(g.letter_grade, 'F') AS letter_grade, " +
                     "COALESCE(g.gpa_point, 0.00) AS gpa_point " +
                     "FROM enrollments e " +
                     "JOIN users u ON e.student_id = u.id " +
                     "LEFT JOIN grades g ON e.id = g.enrollment_id " +
                     "WHERE e.class_section_id = ? " +
                     "ORDER BY u.full_name ASC";
        
        return executeQuery(sql, this::mapResultSetToGrade, classSectionId);
    }

    public boolean saveGrade(int enrollmentId, double attendanceScore, double assignmentScore, 
                             double midtermScore, double finalScore, double totalScore, 
                             String letterGrade, double gpaPoint) {
        String sql = "INSERT INTO grades (enrollment_id, attendance_score, assignment_score, midterm_score, final_score, total_score, letter_grade, gpa_point) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?) " +
                     "ON DUPLICATE KEY UPDATE " +
                     "attendance_score = ?, assignment_score = ?, midterm_score = ?, final_score = ?, total_score = ?, letter_grade = ?, gpa_point = ?";
                     
        return executeUpdate(sql, 
                enrollmentId, attendanceScore, assignmentScore, midtermScore, finalScore, totalScore, letterGrade, gpaPoint,
                attendanceScore, assignmentScore, midtermScore, finalScore, totalScore, letterGrade, gpaPoint) > 0;
    }

    private Grade mapResultSetToGrade(ResultSet rs) throws SQLException {
        Grade grade = new Grade();
        grade.setEnrollmentId(rs.getInt("enrollment_id"));
        grade.setStudentId(rs.getInt("student_id"));
        grade.setStudentIdentifier(rs.getString("student_identifier"));
        grade.setStudentName(rs.getString("student_name"));
        
        grade.setAttendanceScore(rs.getDouble("attendance_score"));
        grade.setAssignmentScore(rs.getDouble("assignment_score"));
        grade.setMidtermScore(rs.getDouble("midterm_score"));
        grade.setFinalScore(rs.getDouble("final_score"));
        grade.setTotalScore(rs.getDouble("total_score"));
        grade.setLetterGrade(rs.getString("letter_grade"));
        grade.setGpaPoint(rs.getDouble("gpa_point"));
        return grade;
    }
}
