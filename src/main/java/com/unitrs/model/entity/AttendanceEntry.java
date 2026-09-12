package com.unitrs.model.entity;

public class AttendanceEntry {
    private int id;
    private int attendanceRecordId;
    private int studentId;
    private String status;

    private String studentName;
    private String studentIdentifier;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getAttendanceRecordId() { return attendanceRecordId; }
    public void setAttendanceRecordId(int attendanceRecordId) { this.attendanceRecordId = attendanceRecordId; }

    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public String getStudentIdentifier() { return studentIdentifier; }
    public void setStudentIdentifier(String studentIdentifier) { this.studentIdentifier = studentIdentifier; }
}
