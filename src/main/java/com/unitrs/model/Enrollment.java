package com.unitrs.model;

import java.sql.Timestamp;

public class Enrollment {

    private int id;
    private int studentId;
    private int classSectionId;
    private Timestamp enrolledAt;

    private String studentIdentifier;
    private String studentName;
    private String studentEmail;
    private String courseCode;
    private String courseTitle;
    private int credits;
    private SessionShift sessionShift;
    private String room;
    private String daysOfWeek;
    private String professorName;
    private String termName;
    private String academicYear;

    public Enrollment() {}

    public Enrollment(int id, int studentId, int classSectionId, Timestamp enrolledAt) {
        this.id = id;
        this.studentId = studentId;
        this.classSectionId = classSectionId;
        this.enrolledAt = enrolledAt;
    }

    public Enrollment(int studentId, int classSectionId) {
        this.studentId = studentId;
        this.classSectionId = classSectionId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getClassSectionId() {
        return classSectionId;
    }

    public void setClassSectionId(int classSectionId) {
        this.classSectionId = classSectionId;
    }

    public Timestamp getEnrolledAt() {
        return enrolledAt;
    }

    public void setEnrolledAt(Timestamp enrolledAt) {
        this.enrolledAt = enrolledAt;
    }

    public String getStudentIdentifier() {
        return studentIdentifier;
    }

    public void setStudentIdentifier(String studentIdentifier) {
        this.studentIdentifier = studentIdentifier;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public SessionShift getSessionShift() {
        return sessionShift;
    }

    public void setSessionShift(SessionShift sessionShift) {
        this.sessionShift = sessionShift;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getDaysOfWeek() {
        return daysOfWeek;
    }

    public void setDaysOfWeek(String daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }

    public String getProfessorName() {
        return professorName;
    }

    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }

    public String getTermName() {
        return termName;
    }

    public void setTermName(String termName) {
        this.termName = termName;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }
}