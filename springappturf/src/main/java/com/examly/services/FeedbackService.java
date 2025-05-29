package com.examly.services;

import java.util.List;

import com.examly.ExceptionHandling.TurfExceptions;
import com.examly.model.Booking;
import com.examly.model.Feedback;

public interface FeedbackService {
     public List<Feedback> getFeedbacks();
    public void addFeedback(Feedback b);
    public void editFeedback(Feedback b);
    public void deleteFeedback(Long feedbackId);
 public Feedback getFeedbacksByFeedbackId(Long feedbackId) throws TurfExceptions;
}
