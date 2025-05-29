package com.examly.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.repository.TurfRepo;

@Service
public class TurfServiceImpl implements TurfService{
    @Autowired
    TurfRepo repo;
    

}
