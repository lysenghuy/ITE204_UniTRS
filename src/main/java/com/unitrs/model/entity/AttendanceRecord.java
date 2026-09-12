package com.unitrs.model.entity;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class AttendanceRecord {
    private int id;
    private int classSectionId;
    private Date sessionDate;
    private Timestamp createdAt;

    private int presentCount;
    private int absentCount;
    private int lateCount;
    private int excusedCount;

    private List<AttendanceEntry> entries;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getClassSectionId() { return classSectionId; }
    public void setClassSectionId(int classSectionId) { this.classSectionId = classSectionId; }

    public Date getSessionDate() { return sessionDate; }
    public void setSessionDate(Date sessionDate) { this.sessionDate = sessionDate; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }

    public List<AttendanceEntry> getEntries() { return entries; }
    public void setEntries(List<AttendanceEntry> entries) { this.entries = entries; }

    public int getPresentCount() { return presentCount; }
    public void setPresentCount(int presentCount) { this.presentCount = presentCount; }

    public int getAbsentCount() { return absentCount; }
    public void setAbsentCount(int absentCount) { this.absentCount = absentCount; }

    public int getLateCount() { return lateCount; }
    public void setLateCount(int lateCount) { this.lateCount = lateCount; }

    public int getExcusedCount() { return excusedCount; }
    public void setExcusedCount(int excusedCount) { this.excusedCount = excusedCount; }
}
