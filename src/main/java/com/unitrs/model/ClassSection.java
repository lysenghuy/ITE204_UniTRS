package com.unitrs.model;

public class ClassSection {

    private int id;
    private int termId;
    private int courseId;
    private int professorId;
    private SessionShift sessionShift;
    private String room;
    private String daysOfWeek;
    private String academicYear;

    private String courseCode;
    private String courseTitle;
    private int credits;
    private String professorName;
    private String termName;

    public ClassSection() {}

    public ClassSection(int id, int termId, int courseId, int professorId,
                        SessionShift sessionShift, String room, String daysOfWeek, String academicYear) {
        this.id = id;
        this.termId = termId;
        this.courseId = courseId;
        this.professorId = professorId;
        this.sessionShift = sessionShift;
        this.room = room;
        this.daysOfWeek = daysOfWeek;
        this.academicYear = academicYear;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTermId() {
        return termId;
    }

    public void setTermId(int termId) {
        this.termId = termId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getProfessorId() {
        return professorId;
    }

    public void setProfessorId(int professorId) {
        this.professorId = professorId;
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

    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
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
}