package com.unitrs.model;

public class Term {

    private int id;
    private int termNumber;
    private String termName;

    public Term() {
    }

    public Term(int id, int termNumber, String termName) {
        this.id = id;
        this.termNumber = termNumber;
        this.termName = termName;
    }

    public Term(int termNumber, String termName) {
        this.termNumber = termNumber;
        this.termName = termName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTermNumber() {
        return termNumber;
    }

    public void setTermNumber(int termNumber) {
        this.termNumber = termNumber;
    }

    public String getTermName() {
        return termName;
    }

    public void setTermName(String termName) {
        this.termName = termName;
    }
}