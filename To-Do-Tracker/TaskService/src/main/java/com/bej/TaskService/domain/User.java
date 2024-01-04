package com.bej.TaskService.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class User {

//    private String userId;
    private String userName;
    private String userPassword;
    @Id
    private String email;
    private Long userphoneNumber;
    private List<Task> list;


    public User(String userName, String userPassword, String email, Long userphoneNumber, List<Task> list) {
//        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.email = email;
        this.userphoneNumber = userphoneNumber;
        this.list = list;
    }

//    public String getUserId() {
//        return userId;
//    }
//
//    public void setUserId(String userId) {
//        this.userId = userId;
//    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getUserphoneNumber() {
        return userphoneNumber;
    }

    public void setUserphoneNumber(Long userphoneNumber) {
        this.userphoneNumber = userphoneNumber;
    }

    public List<Task> getList() {
        return list;
    }

    public void setList(List<Task> list) {
        this.list = list;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
//                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", email='" + email + '\'' +
                ", userphoneNumber=" + userphoneNumber +
                ", list=" + list +
                '}';
    }
}