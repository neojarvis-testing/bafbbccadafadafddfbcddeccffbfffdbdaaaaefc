package main.java.com.examly.DTO;

@Entity
public class Turf {

    @Id 
    @GeneratedValue(strategy=GeneratedValue.Identity)
    int turfId;
    String name;
    String location;
    int pricePerHour;
    String description;
    Boolean isActive;
    String photo;
    @ManyToOne
    @JoinColumn(name = "user_Id")
    User user;
    
    

}
