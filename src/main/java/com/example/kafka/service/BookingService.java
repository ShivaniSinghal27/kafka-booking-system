package com.example.kafka.service;


import com.example.kafka.entity.BookingEntity;
import com.example.kafka.model.BookingEvent;
import com.example.kafka.repo.BookingRepository;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookingService {

    private final BookingRepository repository;

    public BookingService(BookingRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void handleEvent(BookingEvent event) {
        String bookingId = event.getBookingId();

        switch (event.getType()) {
            case NEW_BOOKING -> {
                if (!repository.existsByBookingId(bookingId)) {
                    repository.save(new BookingEntity(bookingId, event.getPayload()));
                    System.out.println("Created booking " + bookingId);
                } else {
                    System.out.println("Booking already exists " + bookingId + " - ignoring NEW_BOOKING");
                }
            }
            case CHANGE_BOOKING -> {
                if (!repository.existsByBookingId(bookingId)) {
                    System.out.println("Booking not found for change: " + bookingId + " -> will retry");
                    throw new RecoverableDataAccessException("Booking not found yet: " + bookingId);
                }
                BookingEntity existing = repository.findById(bookingId).orElseThrow();
                existing.setPayload(event.getPayload());
                repository.save(existing);
                System.out.println("Changed booking " + bookingId);
            }
            case CANCEL_BOOKING -> {
                if (repository.existsByBookingId(bookingId)) {
                    repository.deleteById(bookingId);
                    System.out.println("Cancelled booking " + bookingId);
                } else {
                    System.out.println("Cancel requested for non-existent booking " + bookingId + " - ignoring");
                }
            }
            default -> throw new IllegalArgumentException("Unsupported event type: " + event.getType());
        }
    }
}
