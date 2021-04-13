package nl.novi.stuivenberg.springboot.example.security.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResidenceTest {

    private Residence residence;

    @BeforeEach
    void setUp() {
        this.residence = new Residence("null", "null", "null", "null");
    }

    @Test
    void testGetAvatarData() {
        String expectedAvatarData = "null null null null";
        String actualAvatarData = this.residence.getAvatarData();
        assertEquals(expectedAvatarData, actualAvatarData);
    }
}
