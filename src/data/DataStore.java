package data;

import model.*;

import java.util.ArrayList;
import java.util.List;

public class DataStore {
    private static List<User> users = new ArrayList<>();
    private static List<StudentProfile> students = new ArrayList<>();
    private static List<Course> courses = new ArrayList<>();
    private static List<Enrollment> enrollments = new ArrayList<>();
    private static List<GradeRecord> grades = new ArrayList<>();

    static {
        users.add(new User("admin", "1234", "ADMIN", "System Admin", "A001"));
        users.add(new User("instructor", "1234", "INSTRUCTOR", "Demo Instructor", "I001"));
        users.add(new User("mertkiyar", "1234", "STUDENT", "Mert Kiyar", "S001"));
        users.add(new User("harrypotter", "1234", "STUDENT", "Harry Potter", "S001"));
        users.add(new User("hermionegranger", "1234", "STUDENT", "Hermione Granger", "S001"));
        users.add(new User("ronweasley", "1234", "STUDENT", "Ron Weasley", "S001"));

        courses.add(new Course("Math 101", "MATH101", 3, 30, "instructor"));
        courses.add(new Course("Physics 101", "PHYS101", 4, 25, "instructor"));

        students.add(new StudentProfile("S001", "Mert Kiyar", "Computer Science", 2, "mertkiyar"));
        students.add(new StudentProfile("S002", "Harry Potter", "Computer Science", 2, "harrypotter"));
        students.add(new StudentProfile("S003", "Hermione Granger", "Computer Science", 2, "hermionegranger"));
        students.add(new StudentProfile("S004", "Ron Weasley", "Computer Science", 3, "ronweasley"));

        enrollments.add(new Enrollment("mertkiyar", "MATH101"));
        enrollments.add(new Enrollment("harrypotter", "MATH101"));
        enrollments.add(new Enrollment("hermionegranger", "MATH101"));
        enrollments.add(new Enrollment("hermionegranger", "PHYS101"));
        enrollments.add(new Enrollment("mertkiyar", "PHYS101"));
        enrollments.add(new Enrollment("ronweasley", "PHYS101"));
    }

    public static User findUser(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public static List<User> getUsers() { return users; }
    public static List<Course> getCourses() { return courses; }
    public static List<StudentProfile> getStudents() { return students; }
    public static List<Enrollment> getEnrollments() { return enrollments; }
    public static List<GradeRecord> getGrades() { return grades; }

    public static List<Course> getCoursesByInstructor(String instructorUsername) {
        List<Course> result = new ArrayList<>();
        for (Course course : courses) {
            if (course.getInstructorUsername().equals(instructorUsername)) {
                result.add(course);
            }
        }
        return result;
    }

    public static List<StudentProfile> getStudentsByCourse(String courseCode) {
        List<StudentProfile> result = new ArrayList<>();
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getCourseCode().equals(courseCode)) {
                for (StudentProfile student : students) {
                    if (student.getUsername().equals(enrollment.getStudentUsername())) {
                        result.add(student);
                    }
                }
            }
        }
        return result;
    }

    public static GradeRecord findGrade(String studentUsername, String courseCode) {
        for (GradeRecord grade : grades) {
            if (grade.getStudentUsername().equals(studentUsername)
                    && grade.getCourseCode().equals(courseCode)) {
                return grade;
            }
        }
        return null;
    }

    public static void saveGrade(String studentUsername, String courseCode, double midterm, double finalExam) {
        GradeRecord existing = findGrade(studentUsername, courseCode);
        if (existing != null) {
            existing.setMidtermExam(midterm);
            existing.setFinalExam(finalExam);
        } else {
            grades.add(new GradeRecord(studentUsername, courseCode, midterm, finalExam));
        }
    }
}
