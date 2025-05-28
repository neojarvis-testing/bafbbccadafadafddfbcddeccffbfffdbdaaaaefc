package main.java.com.examly.DTO;

import java.lang.annotation.Inherited;

@entity
public class User {

    @Id 
    @GeneratedValue(strategy = generationType.Identity)
    @Column(name = "user_Id")
    int userId;
    String email;
    String password;
    String userName;
    int mobileNumber;
   String userRole;

   public User(int userId, String email, String password, String userName, int mobileNumber, String userRole) {
    this.userId = userId;
    this.email = email;
    this.password = password;
    this.userName = userName;
    this.mobileNumber = mobileNumber;
    this.userRole = userRole;
   }
   public User() {
   }
   public int getUserId() {
    return userId;
   }
   public String getEmail() {
    return email;
   }
   public String getPassword() {
    return password;
   }
   public String getUserName() {
    return userName;
   }
   public int getMobileNumber() {
    return mobileNumber;
   }
   public String getUserRole() {
    return userRole;
   }
   public void setUserId(int userId) {
    this.userId = userId;
   }
   public void setEmail(String email) {
    this.email = email;
   }
   public void setPassword(String password) {
    this.password = password;
   }
   public void setUserName(String userName) {
    this.userName = userName;
   }
   public void setMobileNumber(int mobileNumber) {
    this.mobileNumber = mobileNumber;
   }
   public void setUserRole(String userRole) {
    this.userRole = userRole;
   }
   @Override
   public String toString() {
    return "User [userId=" + userId + ", email=" + email + ", password=" + password + ", userName=" + userName
            + ", mobileNumber=" + mobileNumber + ", userRole=" + userRole + "]";
   }

   



    



}
