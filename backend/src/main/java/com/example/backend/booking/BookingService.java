package com.example.backend.booking;

import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class BookingService {

    private final BookingRepository repository;

    public BookingService(BookingRepository repository) {
        this.repository = repository;
    }

    public void book(String name, String email, Long phone, String property, Date date,String notes){
        Booking booking = new Booking();
        booking.setName(name);
        booking.setEmail(email);
        booking.setPhone(phone);
        booking.setProperty(property);
        booking.setProperty(date.toString());
        booking.setProperty(notes);
        repository.save(booking);
    }
}
