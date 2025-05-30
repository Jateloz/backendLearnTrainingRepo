package com.example.backend.booking;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;

@Controller
public class BookingController {
    private final BookingRepository repository;
    private final BookingService service;

    public BookingController(BookingRepository repository, BookingService service) {
        this.repository = repository;
        this.service = service;
    }

    @PostMapping("/book")
    public String book(
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("phone") Long phone,
            @RequestParam("property") String property,
            @RequestParam("date") Date date,
            @RequestParam("notes") String notes
    ){
        service.book(name,email,phone,property,date,notes);
        return "redirect:/properties";
    }
}
