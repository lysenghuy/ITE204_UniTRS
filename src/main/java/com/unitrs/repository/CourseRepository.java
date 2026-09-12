package com.unitrs.repository;

import com.unitrs.model.entity.Course;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CourseRepository extends BaseRepository {

    public List<Course> findAll() {
        String sql = "SELECT * FROM courses ORDER BY course_code";
        return executeQuery(sql, this::mapResultSetToCourse);
    }

    public Course findById(int id) {
        String sql = "SELECT * FROM courses WHERE id = ?";
        return executeQueryForObject(sql, this::mapResultSetToCourse, id);
    }

    public Course findByCode(String courseCode) {
        String sql = "SELECT * FROM courses WHERE LOWER(course_code) = LOWER(?)";
        return executeQueryForObject(sql, this::mapResultSetToCourse, courseCode);
    }

    public boolean save(Course course) {
        String sql = "INSERT INTO courses (course_code, course_title, credits) VALUES (?, ?, ?)";
        return executeUpdate(sql, course.getCourseCode(), course.getCourseTitle(), course.getCredits()) > 0;
    }

    public boolean update(Course course) {
        String sql = "UPDATE courses SET course_code = ?, course_title = ?, credits = ? WHERE id = ?";
        return executeUpdate(sql, course.getCourseCode(), course.getCourseTitle(), course.getCredits(), course.getId()) > 0;
    }

    private Course mapResultSetToCourse(ResultSet rs) throws SQLException {
        Course course = new Course();
        course.setId(rs.getInt("id"));
        course.setCourseCode(rs.getString("course_code"));
        course.setCourseTitle(rs.getString("course_title"));
        course.setCredits(rs.getInt("credits"));
        return course;
    }
}
