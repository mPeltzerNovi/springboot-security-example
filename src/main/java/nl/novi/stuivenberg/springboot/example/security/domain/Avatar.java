package nl.novi.stuivenberg.springboot.example.security.domain;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "avatar")

public class Avatar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //For later additions to the table
    @Column(name = "future_one")
    private String futureOne;

    @Column(name = "last_name")
    private String lastName;

    //For later  to the table
    @Column(name = "future_Two")
    private String futureTwo;

    //Avatar upload
    @Column( length = 2000000)
    private String avatarImage;

    //Constructors
    public Avatar() {

    }

    public Avatar(String futureOne, String lastName, String futureTwo, String avatarImage) {
        this.futureOne = futureOne;
        this.lastName = lastName;
        this.futureTwo = futureTwo;
        this.avatarImage = avatarImage;
    }

    //Relationship
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    //Getters & Setters


    public String getAvatarImage() {
        return avatarImage;
    }

    public void setAvatarImage(String avatarImage) {
        this.avatarImage = avatarImage;
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

    public String getFutureOne() {
        return futureOne;
    }

    public void setFutureOne(String futureTwo) {
        this.futureOne = futureOne;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFutureTwo() {
        return futureTwo;
    }

    public void setFutureTwo(String futureTwo) {
        this.futureTwo = futureTwo;
    }

    //Methodes
    public String getAvatarData() {
        return this.getFutureOne() + " " + this.getLastName() + " " + this.getFutureTwo() + " " + this.getAvatarImage();
    }

}

