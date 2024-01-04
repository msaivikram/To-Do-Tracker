package com.bej.authentication.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    private String email;
    private String userPassword;

    public User() {
    }

    public User(String email, String userPassword) {
        this.email = email;
        this.userPassword = userPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", userPassword='" + userPassword + '\'' +
                '}';
    }
}
