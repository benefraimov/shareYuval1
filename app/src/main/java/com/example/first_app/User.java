package com.example.first_app;

public class User {
    private String fullName;
    private String email;
    private String phone;

    public User(String email, String fullName, String phone) {
        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
    }

    public User(){}

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
