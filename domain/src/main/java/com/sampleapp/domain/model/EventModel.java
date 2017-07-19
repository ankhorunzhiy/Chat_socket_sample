package com.sampleapp.domain.model;

public class EventModel {

    private String userName;
    private String message;
    private Integer numUsers;
    private Event event;

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

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventModel that = (EventModel) o;

        if (userName != null ? !userName.equals(that.userName) : that.userName != null)
            return false;
        if (message != null ? !message.equals(that.message) : that.message != null) return false;
        if (numUsers != null ? !numUsers.equals(that.numUsers) : that.numUsers != null)
            return false;
        return event == that.event;

    }
}
