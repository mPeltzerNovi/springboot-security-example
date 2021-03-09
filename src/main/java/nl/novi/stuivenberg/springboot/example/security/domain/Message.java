package nl.novi.stuivenberg.springboot.example.security.domain;

import javax.persistence.*;
import java.util.List; //-->Ongebruikt in Booking

@Entity
@Table(name = "message")

public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "client_image")
    private String clientImage;

    @Column(name = "client_name")
    private String clientName;  //Hier zit je waarschijnlijk lastname vs lastName

    @Column(name = "client_text")
    private String clientText;

    //FotoColomn toevoegen:
    @Column( length = 2000000)
    private String contestImage;

    public Message() {

    }

    //Kijk; er is ook geen code voor "getContestImage" maar wel voor getUser!!!!
    public String getContestImage() {
        return contestImage;
    }

    public void setContestImage(String contestImage) {
        this.contestImage = contestImage;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    //Het rood van ".setUser" in de @Postmapping van MessageController ging weg na aanmaken
    // getters en setters van "User" in "Message"




    //Relatie toevoegen (Frankfilm3 rond 01:09:01):
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getClientImage() {
        return clientImage;
    }

    public void setClientImage(String clientImage) {
        this.clientImage = clientImage;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientText() {
        return clientText;
    }

    public void setClientText(String clientText) {
        this.clientText = clientText;
    }
}
