package model;

public class Enrollment {
    private String studentUsername;
    private String courseCode;

    public Enrollment(String studentUsername, String courseCode) {
        this.studentUsername = studentUsername;
        this.courseCode = courseCode;
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
}
