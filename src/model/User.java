package model;

public class User {
    private String username;
    private String password;
    private String role;
    private String fullName;
    private String referenceID;

    public User(String username, String password, String role, String fullName, String referenceID) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.fullName = fullName;
        this.referenceID = referenceID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getReferenceID() {
        return referenceID;
    }

    public void setReferenceID(String referenceID) {
        this.referenceID = referenceID;
    }

    public String toFileString() {
        return super.toString();
    }
}
