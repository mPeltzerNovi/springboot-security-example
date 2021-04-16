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

    //Hier nog eea veranderen het gaat om de avatarFoto dus het moet ook
    //testGetAvatarData gaan heten overal. En avatarImage dan geen "null" maar naam maken etc
    //Deze is dus voorlopig maar het blijkt dat de foto upload wel werkt.
    @Test
    public void testGetAvatarByLastName() {
        avatar = new Avatar("null", "null", "null", "null");

        Mockito
                .when(avatarRepository.findByLastNameIgnoreCase(avatar.getLastName()))
                .thenReturn(avatar);

        String lastname = "null";
        String expected = "null null null null";

        Avatar found = avatarService.getAvatarByLastName(lastname);

        assertEquals(expected, found.getAvatarData());
    }

	/*@Test
	void contextLoads() {
	}*/

}
