package com.examly.model;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Feedback {

    @Id 
    @GeneratedValue(strategy=GeneratedValue.Identity)
    Long feedbackId;
    String feedbackText;
    int rating;
    LocalDate date;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_Id",referencedColumnName = "user_Id")
    User user;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "turfId",referencedColumnName = "turf_Id")
    Turf turf;

    public Feedback() {
    }

    public Feedback(Long feedbackId, String feedbackText, int rating, LocalDate date, User user, Turf turf) {
        this.feedbackId = feedbackId;
        this.feedbackText = feedbackText;
        this.rating = rating;
        this.date = date;
        this.user = user;
        this.turf = turf;
    }

    public Long getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Long feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getFeedbackText() {
        return feedbackText;
    }

    public void setFeedbackText(String feedbackText) {
        this.feedbackText = feedbackText;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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

    @Override
    public String toString() {
        return "Feedback [feedbackId=" + feedbackId + ", feedbackText=" + feedbackText + ", rating=" + rating
                + ", date=" + date + ", user=" + user + ", turf=" + turf + "]";
    }


    

}
