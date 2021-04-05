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

    public Booking(){

    }

    //Dit is er bij gekomen
    /*public Booking(String arrival, String departure, String comment, String baseImage) {
        this.arrival = arrival;
        this.departure = departure;
        this.comment = comment;
        this.baseImage = baseImage;
    }*/
    //Einde dit is erbij gekomen


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

    /*public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getClientNr() {
        return clientNr;
    }

    public void setClientNr(String clientNr) {
        this.clientNr = clientNr;
    }*/
}
