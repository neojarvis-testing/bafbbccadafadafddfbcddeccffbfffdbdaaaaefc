package com.examly.model;

import java.lang.annotation.Inherited;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Booking")
public class Booking {

    @Id 
    @GeneratedValue(strategy = GeneratedValue.IDENTITY)
    int bookingId;

    LocalDate bookingForDate;
    LocalTime startTime;
    LocalTime endTime;
    LocalDateTime bookingCreatedAt;
    String status;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_Id",referencedColumnName = "user_Id")
    User user;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "turfId",referencedColumnName = "turf_Id")
    Turf turf;

    

    public Booking(int bookingId, LocalDate bookingForDate, LocalTime startTime, LocalTime endTime,
            LocalDateTime bookingCreatedAt, String status, User user, Turf turf) {
        this.bookingId = bookingId;
        this.bookingForDate = bookingForDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.bookingCreatedAt = bookingCreatedAt;
        this.status = status;
        this.user = user;
        this.turf = turf;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public LocalDate getBookingForDate() {
        return bookingForDate;
    }

    public void setBookingForDate(LocalDate bookingForDate) {
        this.bookingForDate = bookingForDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public LocalDateTime getBookingCreatedAt() {
        return bookingCreatedAt;
    }

    public void setBookingCreatedAt(LocalDateTime bookingCreatedAt) {
        this.bookingCreatedAt = bookingCreatedAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Turf getTurf() {
        return turf;
    }

    public void setTurf(Turf turf) {
        this.turf = turf;
    }

    public Booking() {
    }

    @Override
    public String toString() {
        return "Booking [bookingId=" + bookingId + ", bookingForDate=" + bookingForDate + ", startTime=" + startTime
                + ", endTime=" + endTime + ", bookingCreatedAt=" + bookingCreatedAt + ", status=" + status + ", user="
                + user + ", turf=" + turf + "]";
    }

    


}
