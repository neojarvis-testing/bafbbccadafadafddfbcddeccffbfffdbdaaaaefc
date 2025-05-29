package com.examly.model;

import java.lang.annotation.Inherited;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "User")
public class User {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_Id")
    Long userId;
    String email;
    String password;
    String userName;
    String mobileNumber;
   String userRole;
   public User(Long userId, String email, String password, String userName, String mobileNumber, String userRole) {
    this.userId = userId;
    this.email = email;
    this.password = password;
    this.userName = userName;
    this.mobileNumber = mobileNumber;
    this.userRole = userRole;
   }
   public Long getUserId() {
    return userId;
   }
   public void setUserId(Long userId) {
    this.userId = userId;
   }
   public String getEmail() {
    return email;
   }
   public void setEmail(String email) {
    this.email = email;
   }
   public String getPassword() {
    return password;
   }
   public void setPassword(String password) {
    this.password = password;
   }
   public String getUserName() {
    return userName;
   }
   public void setUserName(String userName) {
    this.userName = userName;
   }
   public String getMobileNumber() {
    return mobileNumber;
   }
   public void setMobileNumber(String mobileNumber) {
    this.mobileNumber = mobileNumber;
   }
   public String getUserRole() {
    return userRole;
   }
   public void setUserRole(String userRole) {
    this.userRole = userRole;
   }
   public User() {
   }
   @Override
   public String toString() {
    return "User [userId=" + userId + ", email=" + email + ", password=" + password + ", userName=" + userName
            + ", mobileNumber=" + mobileNumber + ", userRole=" + userRole + "]";
   }

   

}