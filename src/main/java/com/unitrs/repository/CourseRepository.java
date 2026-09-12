package com.unitrs.repository;

import com.unitrs.model.entity.Course;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CourseRepository extends BaseRepository {

    public List<Course> findAll() {
        String sql = "SELECT c.*, s.school_name FROM courses c LEFT JOIN schools s ON c.school_id = s.id ORDER BY c.course_code";
        return executeQuery(sql, this::mapResultSetToCourse);
    }

    public Course findById(int id) {
        String sql = "SELECT c.*, s.school_name FROM courses c LEFT JOIN schools s ON c.school_id = s.id WHERE c.id = ?";
        return executeQueryForObject(sql, this::mapResultSetToCourse, id);
    }

    public Course findByCode(String courseCode) {
        String sql = "SELECT c.*, s.school_name FROM courses c LEFT JOIN schools s ON c.school_id = s.id WHERE LOWER(c.course_code) = LOWER(?)";
        return executeQueryForObject(sql, this::mapResultSetToCourse, courseCode);
    }

    public boolean save(Course course) {
        String sql = "INSERT INTO courses (course_code, course_title, credits, school_id) VALUES (?, ?, ?, ?)";
        return executeUpdate(sql, course.getCourseCode(), course.getCourseTitle(), course.getCredits(), course.getSchoolId()) > 0;
    }

    public boolean update(Course course) {
        String sql = "UPDATE courses SET course_code = ?, course_title = ?, credits = ?, school_id = ? WHERE id = ?";
        return executeUpdate(sql, course.getCourseCode(), course.getCourseTitle(), course.getCredits(), course.getSchoolId(), course.getId()) > 0;
    }

    private Course mapResultSetToCourse(ResultSet rs) throws SQLException {
        Course course = new Course();
        course.setId(rs.getInt("id"));
        course.setCourseCode(rs.getString("course_code"));
        course.setCourseTitle(rs.getString("course_title"));
        course.setCredits(rs.getInt("credits"));
        
        course.setSchoolId(rs.getInt("school_id"));
        course.setSchoolName(rs.getString("school_name"));
        return course;
    }
}
