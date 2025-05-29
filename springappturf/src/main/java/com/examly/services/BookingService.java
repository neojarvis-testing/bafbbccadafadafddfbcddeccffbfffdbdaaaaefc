package com.examly.services;

import java.util.List;

import com.examly.ExceptionHandling.TurfExceptions;
import com.examly.model.Booking;

public interface BookingService {

     public List<Booking> getBookings();
    public void addBooking(Booking b);
    public void editBooking(Booking b);
    public void deleteBooking(Long bookingId);
 public Booking getBookingsbyBookingId(Long bookingId) throws TurfExceptions;


}
