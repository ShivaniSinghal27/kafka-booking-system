package com.example.kafka.service;


import com.example.kafka.model.BookingEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class BookingProducer {

    private final KafkaTemplate<String, BookingEvent> kafkaTemplate;
    private final String topic = "booking-commands";

    public BookingProducer(KafkaTemplate<String, BookingEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendBookingEvent(BookingEvent event) {
        kafkaTemplate.send(topic, event.getBookingId(), event)
                .whenComplete((meta, ex) -> {
                    if (ex == null) {
                        System.out.printf("SENT key=%s partition=%d offset=%d%n",
                                event.getBookingId(), meta.getRecordMetadata().partition(), meta.getRecordMetadata().offset());
                    } else {
                        System.err.println("Failed to send: " + ex.getMessage());
                    }
                });
    }
}
