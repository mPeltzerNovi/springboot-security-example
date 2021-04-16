package nl.novi.stuivenberg.springboot.example.security.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AvatarTest {

    private Avatar avatar;

    @BeforeEach
    void setUp() {
        this.avatar = new Avatar("null", "null", "null", "null");
    }

    @Test
    void testGetAvatarData() {
        String expectedAvatarData = "null null null null";
        String actualAvatarData = this.avatar.getAvatarData();
        assertEquals(expectedAvatarData, actualAvatarData);
    }
}
