package com.example.kafka.repo;


import com.example.kafka.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<BookingEntity, String> {
    boolean existsByBookingId(String bookingId);
}
