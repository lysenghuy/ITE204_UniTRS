package com.unitrs.service;

import com.unitrs.model.entity.Course;
import com.unitrs.model.entity.Term;
import java.util.List;
import java.util.Map;

public interface DeanService {

    // --- Course Management ---
    List<Course> getAllCourses();

    Course getCourseById(int id);

    void addCourse(String courseCode, String courseTitle, int credits, int schoolId);

    void updateCourse(int id, String courseCode, String courseTitle, int credits, int schoolId);

    List<com.unitrs.model.entity.School> getAllSchools();

    // --- Term Management ---
    List<Term> getAllTerms();

    Term getTermById(int id);

    void addTerm(int termNumber, String termName);

    void updateTerm(int id, int termNumber, String termName);

    // --- Curriculum Bundling ---
    void assignCourseToTerm(int termId, int courseId);

    void removeCourseFromTerm(int termId, int courseId);

    // Returns a map where the key is the Term, and the value is a list of Courses
    // assigned to it.
    Map<Term, List<Course>> getTermCurriculumMap();

    // --- Faculty & Scheduling ---
    List<com.unitrs.model.entity.User> getAllProfessors();

    List<com.unitrs.model.entity.ClassSection> getAllClassSections();

    void addClassSection(int termId, int courseId, int professorId, int roomId, String sessionShift, String daysOfWeek,
            String academicYear);

    void removeClassSection(int id);

    // --- Facilities & Rooms ---
    List<com.unitrs.model.entity.Room> getAllRooms();

    void addRoom(String roomNumber, int floorNumber, int capacity);

    void addRoomsBatch(int floorNumber, int numberOfRooms, int capacityPerRoom);

    void deleteRoom(int id);
}
