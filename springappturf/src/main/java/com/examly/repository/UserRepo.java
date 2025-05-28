package main.java.com.examly.repository;

import java.util.Optional;

import main.java.com.examly.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, String>{

    @Query("select u from User u where u.email=:email where u.password=:password")
    public User validate(@Param ("email") String email,@Param("password") String password);

    }


