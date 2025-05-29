package com.examly.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.ExceptionHandling.TurfExceptions;
import com.examly.model.Booking;
import com.examly.model.Feedback;
import com.examly.repository.FeedBackRepo;

@Service
public class FeedbackServiceImpl implements FeedbackService{
    @Autowired
    FeedBackRepo repo;

     public List<Feedback> getFeedbacks(){
        return repo.findAll();

     }
    public void addFeedback(Feedback b){
        repo.save(b);

    }
    public void editFeedback(Feedback b){
        repo.save(b);

    }
    public void deleteFeedback(Long feedbackId){
        repo.deleteById(feedbackId);

    }
 public Feedback getFeedbacksByFeedbackId(Long feedbackId) throws TurfExceptions{
     if(repo.existsById(feedbackId)){
            Optional<Feedback> o = repo.findById(feedbackId);
            Feedback b = o.get();
            return b;
        } else
            throw new TurfExceptions("not found");

 }


}
