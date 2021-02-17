package nl.novi.stuivenberg.springboot.example.security.domain;

import javax.persistence.*;

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
