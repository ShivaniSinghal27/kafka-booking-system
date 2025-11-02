package com.example.kafka.controller;


import com.example.kafka.model.BookingEvent;
import com.example.kafka.model.BookingEventType;
import com.example.kafka.service.BookingProducer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingController {

    private final BookingProducer producer;

    public BookingController(BookingProducer producer) {
        this.producer = producer;
    }

    @PostMapping("/api/bookings/send")
    public String send(
            @RequestParam String bookingId,
            @RequestParam String type,
            @RequestParam(required = false) String payload) {

        BookingEventType eventType = BookingEventType.valueOf(type);
        BookingEvent event = new BookingEvent(bookingId, eventType, payload == null ? "{}" : payload);
        producer.sendBookingEvent(event);
        return "queued";
    }
}
