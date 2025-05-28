package main.java.com.examly.DTO;

import java.lang.annotation.Inherited;

@entity
public class User {

    @Id 
    @GeneratedValue(strategy = generationType.Identity)
    int userId;
    String email;
    String password;
    String userName;
    int mobileNumber;
   String userRole;

   



    



}
