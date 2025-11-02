package com.example.kafka.service;


import com.example.kafka.model.BookingEvent;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.retry.annotation.Backoff;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class BookingConsumer {

    private final BookingService bookingService;

    public BookingConsumer(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @RetryableTopic(
            attempts = "4",
            backoff = @Backoff(delay = 2000, multiplier = 2),
            dltTopicSuffix = "-dlt",
            include = {RecoverableDataAccessException.class}
    )
    @KafkaListener(topics = "booking-commands", groupId = "booking-group")
    public void listen(ConsumerRecord<String, BookingEvent> record, Acknowledgment ack) {
        BookingEvent event = record.value();
        System.out.printf("CONSUME partition=%d offset=%d key=%s event=%s%n",
                record.partition(), record.offset(), record.key(), event);

        bookingService.handleEvent(event);
        ack.acknowledge();
    }
}
