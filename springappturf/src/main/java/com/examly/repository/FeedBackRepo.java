package com.examly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examly.model.Feedback;

@Repository
public interface FeedBackRepo extends JpaRepository<Feedback, Long> {


}
