package com.examly.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.ExceptionHandling.TurfExceptions;
import com.examly.model.Booking;
import com.examly.repository.BookingRepo;

@Service
public class BookingServiceImpl implements BookingService{

    @Autowired
    BookingRepo repo;

     public List<Booking> getBookings(){
      return  repo.findAll();

     }
    public void addBooking(Booking b){
        repo.save(b);

    }
    public void editBooking(Booking b){
        repo.save(b);

    }
    public void deleteBooking(Long bookingId){
        repo.deleteById(bookingId);

    }

    public Booking getBookingsbyBookingId(Long bookingId) throws TurfExceptions{
        if(repo.existsById(bookingId)){
            Optional<Booking> o = repo.findById(bookingId);
            Booking b = o.get();
            return b;
        } else
            throw new TurfExceptions("not found");

    }

}
