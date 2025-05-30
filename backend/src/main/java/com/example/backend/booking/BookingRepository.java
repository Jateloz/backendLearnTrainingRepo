package com.example.backend.booking;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking,Long> {
    boolean existsByEmail(String email);
    Booking findByEmail(String email);
}
