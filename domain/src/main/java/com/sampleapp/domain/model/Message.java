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

    private Message(){}

    public Message from(EventModel eventModel){
        Message message = new Message();
        message.message = eventModel.getMessage();
        message.userName = eventModel.getUserName();
        return message;
    }

    public static Message from(String userName, String messageText){
        Message message = new Message();
        message.message = messageText;
        message.userName = userName;
        return message;
    }

}
