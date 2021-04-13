package nl.novi.stuivenberg.springboot.example.security.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

    private User user;

    @BeforeEach
    void setUp() {

        this.user = new User ("Martin", "martin@novi.nl");
    }

    @Test
    void testGetUserAndAdress() {
        String expectedUsernameAndAddress = "Martin martin@novi.nl";
        String actualUsernameAndAddress = this.user.getUsernameAndAddress();
        assertEquals(expectedUsernameAndAddress, actualUsernameAndAddress);
    }
}
