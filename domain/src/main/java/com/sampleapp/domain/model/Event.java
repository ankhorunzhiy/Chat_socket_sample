package com.sampleapp.domain.model;


public enum Event {
    NEW_MESSAGE("new message"),
    CONNECT("connect"),
    DISCONNECT("disconnect"),
    USER_JOINED("user joined"),
    USER_LEFT("user left"),
    TYPING("typing"),
    STOP_TYPING("stop typing"),
    LOGIN("login"),
    ADD_USER("add user");

    private final String event;
    Event(String event) {
        this.event = event;
    }

    public String getEvent() {
        return event;
    }
}
