package com.examly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examly.ExceptionHandling.TurfExceptions;
import com.examly.model.User;
import com.examly.services.UserServiceImpl;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServiceImpl service;

    public String validate(@PathVariable("email") String email, @PathVariable("password") String password) throws TurfExceptions{
        service.validate(email, password);
        return "Login Success";
    }

    public User addUser(@RequestBody User u){
        service.addUser(u);
        return u;
    }


}
