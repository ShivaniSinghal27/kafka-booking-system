package com.example.kafka.service;

import com.example.kafka.model.BookingEvent;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class DltConsumer {

    @KafkaListener(topics = "booking-commands-dlt", groupId = "booking-dlt-group")
    public void dltListen(ConsumerRecord<String, BookingEvent> record) {
        BookingEvent event = record.value();
        System.err.printf("DLT RECEIVED key=%s event=%s partition=%d offset=%d%n",
                record.key(), event, record.partition(), record.offset());
        // TODO: persist to DLT DB table or alert system
    }
}
