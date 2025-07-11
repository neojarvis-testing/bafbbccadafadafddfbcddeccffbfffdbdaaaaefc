package com.examly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examly.model.Booking;

@Repository
public interface BookingRepo extends JpaRepository<Booking, Long> {

}
