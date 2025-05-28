package main.java.com.examly.model;

@Entity
public class Turf {

    @Id 
    @GeneratedValue(strategy=GeneratedValue.Identity)

    @Column(name = "turf_Id")
    int turfId;
    String name;
    String location;
    double pricePerHour;
    String description;
    Boolean isActive;
    String photo;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_Id",referencedColumnName = "user_Id")
    User user;


    
    public Turf(int turfId, String name, String location, double pricePerHour, String description, Boolean isActive,
            String photo, User user) {
        this.turfId = turfId;
        this.name = name;
        this.location = location;
        this.pricePerHour = pricePerHour;
        this.description = description;
        this.isActive = isActive;
        this.photo = photo;
        this.user = user;
    }

    
    public Turf() {
    }


    public int getTurfId() {
        return turfId;
    }
    public void setTurfId(int turfId) {
        this.turfId = turfId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public double getPricePerHour() {
        return pricePerHour;
    }
    public void setPricePerHour(double pricePerHour) {
        this.pricePerHour = pricePerHour;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Boolean getIsActive() {
        return isActive;
    }
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
    public String getPhoto() {
        return photo;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }


    @Override
    public String toString() {
        return "Turf [turfId=" + turfId + ", name=" + name + ", location=" + location + ", pricePerHour=" + pricePerHour
                + ", description=" + description + ", isActive=" + isActive + ", photo=" + photo + ", user=" + user
                + "]";
    }


    

    


}
