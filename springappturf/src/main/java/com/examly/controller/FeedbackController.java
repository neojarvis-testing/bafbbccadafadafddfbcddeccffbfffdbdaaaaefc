package com.examly.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examly.ExceptionHandling.TurfExceptions;
import com.examly.model.Booking;
import com.examly.model.Feedback;
import com.examly.services.FeedbackServiceImpl;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    @Autowired
    FeedbackServiceImpl service;

    @PostMapping
    public Feedback addFeedback(@RequestBody Feedback f){
        service.addFeedback(f);
        return f;
    }

    @PutMapping("/feedbackId")
    public Feedback editFeedback(@RequestBody Feedback f){
        service.editFeedback(f);
        return f;
    }

    @GetMapping
    public List<Feedback> getFeedbacks(Feedback f){
       return service.getFeedbacks();
    }

    @DeleteMapping("/{feedbackId}")
    public String deleteFeedback(Long feedbackId){
        service.deleteFeedback(feedbackId);
        return "deleted";
    }

    @GetMapping("/{feedbackId}")
    public Feedback getFeedbacksById(@PathVariable("feedbackId") Long feedbackId) throws TurfExceptions{
        return service.getFeedbacksByFeedbackId(feedbackId);
    }


}
