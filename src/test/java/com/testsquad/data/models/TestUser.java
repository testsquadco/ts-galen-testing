package com.testsquad.data.models;

public class TestUser {
    private String username;
    private String password;
    private String email;
    private UserRole role;

    public enum UserRole {
        ADMIN, USER, GUEST
    }

    // Getters and setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public UserRole getRole() { return role; }
    public void setRole(UserRole role) { this.role = role; }
} 