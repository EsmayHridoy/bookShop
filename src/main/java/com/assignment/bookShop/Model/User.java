package com.assignment.bookShop.Model;

public class User {
    int userId;
    String password;
    String role;
    String userEmail;
    String userName;

    public User() {
    }

    public User(String password, int userId, String role, String userEmail, String userName) {
        this.password = password;
        this.userId = userId;
        this.role = role;
        this.userEmail = userEmail;
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
