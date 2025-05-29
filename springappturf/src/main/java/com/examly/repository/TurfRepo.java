package com.examly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examly.model.Turf;

@Repository
public interface TurfRepo extends JpaRepository<Turf, Integer> {

}
