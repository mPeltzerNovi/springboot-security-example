package nl.novi.stuivenberg.springboot.example.security;


import nl.novi.stuivenberg.springboot.example.security.domain.Residence;
import nl.novi.stuivenberg.springboot.example.security.repository.ResidenceRepository;
import nl.novi.stuivenberg.springboot.example.security.service.ResidenceService;
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
    private ResidenceService residenceService;

    @MockBean
    private ResidenceRepository residenceRepository;

    @Mock
    Residence residence;

    @BeforeEach
    public void setUp() {

    }

    //Hier nog eea veranderen het gaat om de avatarFoto dus het moet ook
    //testGetAvatarData gaan heten overal. En avatarImage dan geen "null" maar naam maken etc
    //Deze is dus voorlopig maar het blijkt dat de foto upload wel werkt.
    @Test
    public void testGetResidenceByLastName() {
        residence = new Residence("null", "null", "null", "null");

        Mockito
                .when(residenceRepository.findByLastNameIgnoreCase(residence.getLastName()))
                .thenReturn(residence);

        String lastname = "null";
        String expected = "null null null null";

        Residence found = residenceService.getResidenceByLastName(lastname);

        assertEquals(expected, found.getAvatarData());
    }

	/*@Test
	void contextLoads() {
	}*/

}
