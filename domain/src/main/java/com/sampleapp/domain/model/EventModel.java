package com.sampleapp.domain.model;

public class EventModel {

    private String userName;
    private String message;
    private Integer numUsers;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getNumUsers() {
        return numUsers;
    }

    public void setNumUsers(Integer numUsers) {
        this.numUsers = numUsers;
    }
}
