package com.unitrs.util;

import com.unitrs.model.Grade;
import java.util.List;

public class GradeCalculator {

    public static final double MAX_ATTENDANCE = 15.0;
    public static final double MAX_ASSIGNMENT = 25.0;
    public static final double MAX_MIDTERM = 30.0;
    public static final double MAX_FINAL = 30.0;
    public static final double MAX_TOTAL = 100.0;

    private GradeCalculator() {}

    public static double calculateTotal(double attendance, double assignment, double midterm, double finalScore) {
        double total = attendance + assignment + midterm + finalScore;
        if (total < 0) {
            return 0.0;
        }
        return Math.min(total, MAX_TOTAL);
    }

    public static String calculateLetterGrade(double totalScore) {
        if (totalScore >= 85.0) {
            return "A";
        } else if (totalScore >= 80.0) {
            return "B+";
        } else if (totalScore >= 70.0) {
            return "B";
        } else if (totalScore >= 65.0) {
            return "C+";
        } else if (totalScore >= 60.0) {
            return "C";
        } else if (totalScore >= 55.0) {
            return "D+";
        } else if (totalScore >= 50.0) {
            return "D";
        } else {
            return "F";
        }
    }

    public static double calculateGpaPoint(String letterGrade) {
        if (letterGrade == null) {
            return 0.00;
        }
        switch (letterGrade.trim().toUpperCase()) {
            case "A":
                return 4.00;
            case "B+":
                return 3.50;
            case "B":
                return 3.00;
            case "C+":
                return 2.50;
            case "C":
                return 2.00;
            case "D+":
                return 1.50;
            case "D":
                return 1.00;
            default:
                return 0.00;
        }
    }

    public static double calculateGpaPointFromScore(double totalScore) {
        return calculateGpaPoint(calculateLetterGrade(totalScore));
    }

    public static double calculateTermGpa(List<Grade> grades) {
        if (grades == null || grades.isEmpty()) {
            return 0.00;
        }

        double totalWeightedPoints = 0.0;
        int totalCredits = 0;

        for (Grade grade : grades) {
            int credits = grade.getCredits() > 0 ? grade.getCredits() : 3;
            totalWeightedPoints += (grade.getGpaPoint() * credits);
            totalCredits += credits;
        }

        if (totalCredits == 0) {
            return 0.00;
        }

        double gpa = totalWeightedPoints / totalCredits;
        return Math.round(gpa * 100.0) / 100.0;
    }

    public static boolean isValidAttendance(double score) {
        return score >= 0.0 && score <= MAX_ATTENDANCE;
    }

    public static boolean isValidAssignment(double score) {
        return score >= 0.0 && score <= MAX_ASSIGNMENT;
    }

    public static boolean isValidMidterm(double score) {
        return score >= 0.0 && score <= MAX_MIDTERM;
    }

    public static boolean isValidFinal(double score) {
        return score >= 0.0 && score <= MAX_FINAL;
    }

    public static boolean isValidScoreSet(double attendance, double assignment, double midterm, double finalScore) {
        return isValidAttendance(attendance) &&
               isValidAssignment(assignment) &&
               isValidMidterm(midterm) &&
               isValidFinal(finalScore);
    }
}
