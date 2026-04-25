package model;

public class GradeRecord {
    private String studentUsername;
    private String courseCode;
    private double midtermExam;
    private double finalExam;

    public GradeRecord(String studentUsername, String courseCode, double midtermExam, double finalExam) {
        this.studentUsername = studentUsername;
        this.courseCode = courseCode;
        this.midtermExam = midtermExam;
        this.finalExam = finalExam;
    }

    public String getStudentUsername() {
        return studentUsername;
    }

    public void setStudentUsername(String studentUsername) {
        this.studentUsername = studentUsername;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public double getMidtermExam() {
        return midtermExam;
    }

    public void setMidtermExam(double midtermExam) {
        this.midtermExam = midtermExam;
    }

    public double getFinalExam() {
        return finalExam;
    }

    public void setFinalExam(double finalExam) {
        this.finalExam = finalExam;
    }

    public String toFileString() {
        return super.toString();
    }
}
