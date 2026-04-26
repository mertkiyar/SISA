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
        users.add(new User("student", "1234", "STUDENT", "Demo Student", "S001"));
    }

    public static User findUser(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public static List<User> getUsers() {
        return users;
    }
}
