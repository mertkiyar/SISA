package model;

public class GradeRecord {
    private String studentUsername;
    private String courseCode;
    private Integer midtermExam;
    private Integer finalExam;

    public GradeRecord(String studentUsername, String courseCode, Integer midtermExam, Integer finalExam) {
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

    public Integer getMidtermExam() {
        return midtermExam;
    }

    public void setMidtermExam(Integer midtermExam) {
        this.midtermExam = midtermExam;
    }

    public Integer getFinalExam() {
        return finalExam;
    }

    public void setFinalExam(Integer finalExam) {
        this.finalExam = finalExam;
    }
}
