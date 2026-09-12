package com.unitrs.repository;

import com.unitrs.model.entity.School;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SchoolRepository extends BaseRepository {

    public List<School> findAll() {
        String sql = "SELECT * FROM schools ORDER BY id ASC";
        return executeQuery(sql, this::mapResultSetToSchool);
    }

    private School mapResultSetToSchool(ResultSet rs) throws SQLException {
        School school = new School();
        school.setId(rs.getInt("id"));
        school.setSchoolName(rs.getString("school_name"));
        return school;
    }
}
