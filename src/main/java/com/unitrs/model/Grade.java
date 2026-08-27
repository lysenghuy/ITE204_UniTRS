package com.unitrs.model;

import java.sql.Timestamp;

public class Grade {

    private int id;
    private int enrollmentId;
    private double attendanceScore;
    private double assignmentScore;
    private double midtermScore;
    private double finalScore;
    private double totalScore;
    private String letterGrade;
    private double gpaPoint;
    private Timestamp updatedAt;

    private int studentId;
    private String studentIdentifier;
    private String studentName;
    private String studentEmail;
    private String courseCode;
    private String courseTitle;
    private int credits;
    private String termName;
    private int termNumber;
    private SessionShift sessionShift;
    private String academicYear;

    public Grade() {
        this.letterGrade = "F";
    }

    public Grade(int id, int enrollmentId, double attendanceScore, double assignmentScore, 
                 double midtermScore, double finalScore, double totalScore, 
                 String letterGrade, double gpaPoint, Timestamp updatedAt) {
        this.id = id;
        this.enrollmentId = enrollmentId;
        this.attendanceScore = attendanceScore;
        this.assignmentScore = assignmentScore;
        this.midtermScore = midtermScore;
        this.finalScore = finalScore;
        this.totalScore = totalScore;
        this.letterGrade = letterGrade;
        this.gpaPoint = gpaPoint;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(int enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public double getAttendanceScore() {
        return attendanceScore;
    }

    public void setAttendanceScore(double attendanceScore) {
        this.attendanceScore = attendanceScore;
    }

    public double getAssignmentScore() {
        return assignmentScore;
    }

    public void setAssignmentScore(double assignmentScore) {
        this.assignmentScore = assignmentScore;
    }

    public double getMidtermScore() {
        return midtermScore;
    }

    public void setMidtermScore(double midtermScore) {
        this.midtermScore = midtermScore;
    }

    public double getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(double finalScore) {
        this.finalScore = finalScore;
    }

    public double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(double totalScore) {
        this.totalScore = totalScore;
    }

    public String getLetterGrade() {
        return letterGrade;
    }

    public void setLetterGrade(String letterGrade) {
        this.letterGrade = letterGrade;
    }

    public double getGpaPoint() {
        return gpaPoint;
    }

    public void setGpaPoint(double gpaPoint) {
        this.gpaPoint = gpaPoint;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
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

    public String getTermName() {
        return termName;
    }

    public void setTermName(String termName) {
        this.termName = termName;
    }

    public int getTermNumber() {
        return termNumber;
    }

    public void setTermNumber(int termNumber) {
        this.termNumber = termNumber;
    }

    public SessionShift getSessionShift() {
        return sessionShift;
    }

    public void setSessionShift(SessionShift sessionShift) {
        this.sessionShift = sessionShift;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }
}