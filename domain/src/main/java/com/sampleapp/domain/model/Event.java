package com.sampleapp.domain.model;


public enum Event {
    NEW_MESSAGE("new message"),
    CONNECT("connect"),
    DISCONNECT("disconnect"),
    USER_JOINED("user joined"),
    USER_LEFT("user left"),
    TYPING("typing"),
    STOP_TYPING("stop typing");

    private final String eventName;
    Event(String eventName) {
        this.eventName = eventName;
    }

    public String getEventName() {
        return eventName;
    }
}
