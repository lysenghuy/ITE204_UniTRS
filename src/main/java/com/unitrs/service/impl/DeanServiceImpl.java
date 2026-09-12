package com.unitrs.service.impl;

import com.unitrs.exceptions.ValidationException;
import com.unitrs.model.entity.Course;
import com.unitrs.model.entity.Term;
import com.unitrs.repository.CourseRepository;
import com.unitrs.repository.TermRepository;
import com.unitrs.service.DeanService;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DeanServiceImpl implements DeanService {

    private final CourseRepository courseRepository;
    private final TermRepository termRepository;

    public DeanServiceImpl(CourseRepository courseRepository, TermRepository termRepository) {
        this.courseRepository = courseRepository;
        this.termRepository = termRepository;
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course getCourseById(int id) {
        return courseRepository.findById(id);
    }

    @Override
    public void addCourse(String courseCode, String courseTitle, int credits) {
        if (courseCode == null || courseCode.trim().isEmpty()) {
            throw new ValidationException("Course Code cannot be empty.");
        }
        if (courseTitle == null || courseTitle.trim().isEmpty()) {
            throw new ValidationException("Course Title cannot be empty.");
        }
        if (credits <= 0) {
            throw new ValidationException("Credits must be a positive number.");
        }
        
        Course existing = courseRepository.findByCode(courseCode.trim());
        if (existing != null) {
            throw new ValidationException("A course with code " + courseCode + " already exists.");
        }

        Course course = new Course();
        course.setCourseCode(courseCode.trim().toUpperCase());
        course.setCourseTitle(courseTitle.trim());
        course.setCredits(credits);

        if (!courseRepository.save(course)) {
            throw new RuntimeException("Failed to save course.");
        }
    }

    @Override
    public void updateCourse(int id, String courseCode, String courseTitle, int credits) {
        Course course = courseRepository.findById(id);
        if (course == null) {
            throw new ValidationException("Course not found.");
        }
        
        Course existing = courseRepository.findByCode(courseCode.trim());
        if (existing != null && existing.getId() != id) {
            throw new ValidationException("Another course with code " + courseCode + " already exists.");
        }

        course.setCourseCode(courseCode.trim().toUpperCase());
        course.setCourseTitle(courseTitle.trim());
        course.setCredits(credits);

        if (!courseRepository.update(course)) {
            throw new RuntimeException("Failed to update course.");
        }
    }

    @Override
    public List<Term> getAllTerms() {
        return termRepository.findAll();
    }

    @Override
    public Term getTermById(int id) {
        return termRepository.findById(id);
    }

    @Override
    public void addTerm(int termNumber, String termName) {
        if (termNumber <= 0) {
            throw new ValidationException("Term Number must be positive.");
        }
        if (termName == null || termName.trim().isEmpty()) {
            throw new ValidationException("Term Name cannot be empty.");
        }

        Term existing = termRepository.findByNumber(termNumber);
        if (existing != null) {
            throw new ValidationException("Term Number " + termNumber + " already exists.");
        }

        Term term = new Term();
        term.setTermNumber(termNumber);
        term.setTermName(termName.trim());

        if (!termRepository.save(term)) {
            throw new RuntimeException("Failed to save term.");
        }
    }

    @Override
    public void updateTerm(int id, int termNumber, String termName) {
        Term term = termRepository.findById(id);
        if (term == null) {
            throw new ValidationException("Term not found.");
        }

        Term existing = termRepository.findByNumber(termNumber);
        if (existing != null && existing.getId() != id) {
            throw new ValidationException("Another term with number " + termNumber + " already exists.");
        }

        term.setTermNumber(termNumber);
        term.setTermName(termName.trim());

        if (!termRepository.update(term)) {
            throw new RuntimeException("Failed to update term.");
        }
    }

    @Override
    public void assignCourseToTerm(int termId, int courseId) {
        // Will silently ignore if already assigned due to INSERT IGNORE in repository
        termRepository.assignCourseToTerm(termId, courseId);
    }

    @Override
    public void removeCourseFromTerm(int termId, int courseId) {
        termRepository.removeCourseFromTerm(termId, courseId);
    }

    @Override
    public Map<Term, List<Course>> getTermCurriculumMap() {
        List<Term> terms = termRepository.findAll();
        Map<Term, List<Course>> map = new LinkedHashMap<>();
        
        for (Term term : terms) {
            List<Course> courses = termRepository.findCoursesByTerm(term.getId());
            map.put(term, courses);
        }
        
        return map;
    }
}
