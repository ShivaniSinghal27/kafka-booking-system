package com.example.kafka.model;

import java.io.Serializable;

public class BookingEvent implements Serializable {
    private String bookingId;
    private BookingEventType type;
    private String payload;

    public BookingEvent() {}

    public BookingEvent(String bookingId, BookingEventType type, String payload) {
        this.bookingId = bookingId;
        this.type = type;
        this.payload = payload;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public BookingEventType getType() {
        return type;
    }

    public void setType(BookingEventType type) {
        this.type = type;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "BookingEvent{" +
                "bookingId='" + bookingId + '\'' +
                ", type=" + type +
                ", payload='" + payload + '\'' +
                '}';
    }
}
