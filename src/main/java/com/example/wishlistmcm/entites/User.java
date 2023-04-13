package com.example.wishlistmcm.entites;

import java.sql.Date;

public class User {
    private int userId;
    private String fullName;
    private String email;
    private String password;



    public User() {
    }
    public User(int userId, String email, String fullName, String password) {
        this.userId = userId;
        this.email = email;
        this.fullName = fullName;
        this.password = password;
    }

    public User(String email, String fullName, String password) {
        this.email = email;
        this.fullName = fullName;
        this.password = password;
    }
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }



    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
