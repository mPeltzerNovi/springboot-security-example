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
    private String departure;  //Hier zit je waarschijnlijk lastname vs lastName

    @Column(name = "comment")
    private String comment; //comment veranderen in request.

    //Toevoeging voor image
    @Column( length = 2000000)
    private String baseImage; //BaseImage veranderen in passport

    //1maart -->fout
    //@OneToMany(fetch = FetchType.LAZY)
    //private List<User> user;

    //getters en setters erbij!

    //Deze nu even uitgezet
    /*public Booking(){

    }*/

    //Kijken of je die lege kan vervangen door deze hieronde en die dan testen
    //Dit is er bij gekomen constructor om te testen 13 april 21
    public Booking(String arrival, String departure, String comment, String baseImage) {
        this.arrival = arrival;
        this.departure = departure;
        this.comment = comment;
        this.baseImage = baseImage;
    }
    //Einde dit is erbij gekomen
    //Nog een bookingconstructor met alleen arrival en depature maken en testen
    public Booking(String arrival, String departure) {
        this.arrival = arrival;
        this.departure = departure;
    }


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

    //Toevoeging 18feb voor koppelen aan user
    //Een booking kan maar 1 user hebben, een user kan meerdere bookings hebben
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

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

    //GetBooking toegevoegd 13apr21
    public String getBooking() {
        return this.getArrival() + " " + this.getDeparture() + " " + this.getComment() + " " + this.getBaseImage();
    }

    public String getStay() {
        return this.getArrival() + " " + this.getDeparture();
    }


}
