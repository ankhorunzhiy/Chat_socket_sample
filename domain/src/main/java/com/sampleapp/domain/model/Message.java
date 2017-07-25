package com.sampleapp.domain.model;


public class Message {

    private String message;
    private String userName;

    public String getMessage() {
        return message;
    }

    public String getUserName() {
        return userName;
    }

    public Message(String userName, String message) {
        this.userName = userName;
        this.message = message;
    }
}
