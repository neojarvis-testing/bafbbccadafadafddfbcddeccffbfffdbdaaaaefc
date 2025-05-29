package com.examly.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.examly.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer>{

    @Query("select u from User u where u.email=:email where u.password=:password")
    public User validate(@Param ("email") String email,@Param("password") String password);

    }


