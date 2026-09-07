package com.unitrs.model.entity;

public class Course {

    private int id;
    private String courseCode;
    private String courseTitle;
    private int credits;

    public Course() {
        this.credits = 3;
    }

    public Course(int id, String courseCode, String courseTitle, int credits) {
        this.id = id;
        this.courseCode = courseCode;
        this.courseTitle = courseTitle;
        this.credits = credits;
    }

    public Course(String courseCode, String courseTitle, int credits) {
        this.courseCode = courseCode;
        this.courseTitle = courseTitle;
        this.credits = credits;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}