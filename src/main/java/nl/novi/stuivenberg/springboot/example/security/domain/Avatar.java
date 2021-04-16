package nl.novi.stuivenberg.springboot.example.security.domain;

import javax.persistence.*;
import java.util.List; //-->Ongebruikt in Booking


//Deze veranderen naar AvatarUpload en kolommen verwijderen!!!

@Entity
@Table(name = "avatar")

public class Avatar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "future_one")
    private String futureOne;  //deze verwijderen

    @Column(name = "last_name")
    private String lastName;  //Hier zit je waarschijnlijk lastname vs lastName //deze verwijderen

    @Column(name = "future_Two")
    private String futureTwo; //deze verwijderen

    //fotoColomn toevoegen
    @Column( length = 2000000)
    private String avatarImage;

    //Die constructor is ook leeg; daar ook eens naar kijken voor renderen ed
    //Deze lege nu uitzetten 13 apr 21
    public Avatar() {

    }

    //Toevoegen om te testen op 13 april 21
    public Avatar(String futureOne, String lastName, String futureTwo, String avatarImage) {
        this.futureOne = futureOne;
        this.lastName = lastName;
        this.futureTwo = futureTwo;
        this.avatarImage = avatarImage;
    }


    //Relatie toevoegen met User (FrankFilm3 rond 01:09:01)
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    //Nu de getters en setters toevoegen voor de "user" en "avatarImage"


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

    //GetAvatarData toegevoegd 13apr21
    public String getAvatarData() {
        return this.getFutureOne() + " " + this.getLastName() + " " + this.getFutureTwo() + " " + this.getAvatarImage();
    }

}

