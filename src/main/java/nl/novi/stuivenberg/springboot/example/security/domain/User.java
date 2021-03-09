package nl.novi.stuivenberg.springboot.example.security.domain;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "app_user")
public class User  {

    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    @Column(columnDefinition = "serial")
    private long id;
    private String username;
    private String email;
    private String password;

    @ManyToMany
    @JoinTable (name = "user_role",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    //Toevoeging 18 feb user aan booking koppelen:
    //Een user kan meerdere bookings hebben; een booking kan maar een user hebben
    @OneToMany(fetch = FetchType.LAZY) //(mappedBy = "user_id") -->01:15:41 -->Hij haalt het weg bij 01:19:05
    @JoinColumn(name = "booking_id")
    private List<Booking> booking;

    //Verhaal voor de message
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "message_id")
    private List<Message> message;

    //Verhaal voor de residence toegevoegd (9maart21)
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "residence_id")
    private List<Residence> residence;

    public User() {


    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
