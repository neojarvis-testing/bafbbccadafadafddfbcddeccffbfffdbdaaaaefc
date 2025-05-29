package com.examly.services;


import org.springframework.stereotype.Service;

import com.examly.model.User;

public interface UserService {
    public User validate(String email,String password);

}
