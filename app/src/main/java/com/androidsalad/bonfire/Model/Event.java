package com.androidsalad.bonfire.Model;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Event {

    private String eventId;
    private String eventName;
    private String eventAdminId;
    private String eventAdminName;
    private Object eventDate;

    public Event() {
    }

    public Event(String eventId, String eventName, String eventAdminId, String eventAdminName, Object eventDate) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventAdminId = eventAdminId;
        this.eventAdminName = eventAdminName;
        this.eventDate = eventDate;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventAdminId() {
        return eventAdminId;
    }

    public void setEventAdminId(String eventAdminId) {
        this.eventAdminId = eventAdminId;
    }

    public String getEventAdminName() {
        return eventAdminName;
    }

    public void setEventAdminName(String eventAdminName) {
        this.eventAdminName = eventAdminName;
    }

    public Object getEventDate() {
        return eventDate;
    }

    public void setEventDate(Object eventDate) {
        this.eventDate = eventDate;
    }
}
