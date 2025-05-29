package com.examly.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examly.model.Booking;
import com.examly.services.BookingServiceImpl;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    BookingServiceImpl service;

    @PostMapping
    public Booking addBooking(@RequestBody Booking b){
        service.addBooking(b);
        return b;
    }

    @PutMapping("/bookingId")
    public Booking editBooking(@RequestBody Booking b){
        service.editBooking(b);
        return b;
    }

    @GetMapping
    public List<Booking> getBookings(Booking b){
       return service.getBookings();
    }

    @DeleteMapping("/{bookingId}")
    public String deleteBooking(Long BookingId){
        service.deleteBooking(BookingId);
        return "deleted";
    }



}
