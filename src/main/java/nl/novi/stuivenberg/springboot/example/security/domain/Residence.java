package nl.novi.stuivenberg.springboot.example.security.domain;

import javax.persistence.*;
import java.util.List; //-->Ongebruikt in Booking


//Deze veranderen naar AvatarUpload en kolommen verwijderen!!!

@Entity
@Table(name = "residence")

public class Residence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name")
    private String firstName;  //deze verwijderen

    @Column(name = "last_name")
    private String lastName;  //Hier zit je waarschijnlijk lastname vs lastName //deze verwijderen

    @Column(name = "client_nr")
    private String clientNr; //deze verwijderen

    //fotoColomn toevoegen
    @Column( length = 2000000)
    private String avatarImage;

    //Die constructor is ook leeg; daar ook eens naar kijken voor renderen ed
    //Deze lege nu uitzetten 13 apr 21
    /*public Residence() {

    }*/

    //Toevoegen om te testen op 13 april 21
    public Residence(String firstName, String lastName, String clientNr, String avatarImage) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.clientNr = clientNr;
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
    }

}

