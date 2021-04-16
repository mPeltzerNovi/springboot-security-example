package nl.novi.stuivenberg.springboot.example.security.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "booking")

public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "arrival")
    private String arrival;

    @Column(name = "departure")
    private String departure;

    @Column(name = "comment")
    private String comment;

    //add image
    @Column( length = 2000000)
    private String baseImage;

    //Constructors
    public Booking() {

    }

    public Booking(String arrival, String departure, String comment, String baseImage) {
        this.arrival = arrival;
        this.departure = departure;
        this.comment = comment;
        this.baseImage = baseImage;
    }

    public Booking(String arrival, String departure) {
        this.arrival = arrival;
        this.departure = departure;
    }

    //Relationship
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    //Getters & Setters
    public String getBaseImage() {
        return baseImage;
    }

    public void setBaseImage(String baseImage) {
        this.baseImage = baseImage;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    //Methodes
    public String getBooking() {
        return this.getArrival() + " " + this.getDeparture() + " " + this.getComment() + " " + this.getBaseImage();
    }

    public String getStay() {
        return this.getArrival() + " " + this.getDeparture();
    }

}
