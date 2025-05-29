package com.examly.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.model.User;
import com.examly.repository.UserRepo;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepo repo;

    @Override
    public User validate(String email, String password) {
        User u = repo.validate(email,password);
        if(u!=null){
            return u;
        }
    }

}
