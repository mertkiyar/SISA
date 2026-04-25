package model;

public class Course {
    private String courseCode;
    private String courseName;
    private Integer credit;
    private Integer quota;
    private String instructorUsername;

    public Course(String courseName, String courseCode, Integer credit, Integer quota, String instructorUsername) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.credit = credit;
        this.quota = quota;
        this.instructorUsername = instructorUsername;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public Integer getQuota() {
        return quota;
    }

    public void setQuota(Integer quota) {
        this.quota = quota;
    }

    public String getInstructorUsername() {
        return instructorUsername;
    }

    public void setInstructorUsername(String instructorUsername) {
        this.instructorUsername = instructorUsername;
    }
}
