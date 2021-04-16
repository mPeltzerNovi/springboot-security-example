package nl.novi.stuivenberg.springboot.example.security;


import nl.novi.stuivenberg.springboot.example.security.domain.Avatar;
import nl.novi.stuivenberg.springboot.example.security.repository.AvatarRepository;
import nl.novi.stuivenberg.springboot.example.security.service.AvatarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest()

public class NoviExamplesApplicationTestsTwo {

    @Autowired
    private AvatarService avatarService;

    @MockBean
    private AvatarRepository avatarRepository;

    @Mock
    Avatar avatar;

    @BeforeEach
    public void setUp() {

    }

    @Test
    public void testGetAvatarByLastName() {
        avatar = new Avatar("null", "null", "null", "null");

        // Setup our mock repository
        Mockito
                .when(avatarRepository.findByLastNameIgnoreCase(avatar.getLastName()))
                .thenReturn(avatar);

        // Execute the service call
        String lastname = "null";
        String expected = "null null null null";

        Avatar found = avatarService.getAvatarByLastName(lastname);

        // Assert the respone
        assertEquals(expected, found.getAvatarData());
    }

}
