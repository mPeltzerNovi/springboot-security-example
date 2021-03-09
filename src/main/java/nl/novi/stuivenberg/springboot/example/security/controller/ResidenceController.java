package nl.novi.stuivenberg.springboot.example.security.controller;

//


//

//De imports die je specifiek moet maken
import nl.novi.stuivenberg.springboot.example.security.domain.Residence;

import nl.novi.stuivenberg.springboot.example.security.domain.User;
import nl.novi.stuivenberg.springboot.example.security.repository.ResidenceRepository;
import nl.novi.stuivenberg.springboot.example.security.repository.UserRepository;
import nl.novi.stuivenberg.springboot.example.security.service.ResidenceService;

//Overige imports die je kan kopieren
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//Deze ook, ongebruikt in booking en message
import nl.novi.stuivenberg.springboot.example.security.service.UserDetailsImpl;
import nl.novi.stuivenberg.springboot.example.security.service.UserService;

//toegevoegd door kopieren
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;


import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
//@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ResidenceController {

    @Autowired
    private ResidenceService residenceService;

    //Er moest noeg een extra @Autowired worden toegevoegd voor de UserRepository
    @Autowired
    UserRepository userRepository;

    @GetMapping(value = "/residences")
    public ResponseEntity<Object> getResidences() {
        List<Residence> residences = residenceService.getAllResidences();
        return new ResponseEntity<>(residences, HttpStatus.OK);
    }

    //Deze lijkt niet in de uitwerking te staan, wellicht later aangepast-->Deze is omvangrijker 01:10:27 (les 4)
    @GetMapping(value = "/residences/{id}")
    public ResponseEntity<Object> getResidences(@PathVariable("id") long id) {
        Residence residence = residenceService.getResidenceById(id);
        return new ResponseEntity<>(residence, HttpStatus.OK);
    }

    //Mogelijk later net zo aanpassen als in @PostMapping
    @DeleteMapping(value = "/residences/{id}")
    public ResponseEntity<Object> deleteResidence(@PathVariable("id") long id) {
        residenceService.deleteResidence(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); //dit is de foutcode die hij geeft als het niet lukt

    }

    //Aangepast zoals mesage en booking dmv code Frank
    //zal deels aankleuren tot de extra getters en setters zijn aangemaakt
    @PostMapping(value = "/residences")
    public ResponseEntity<Object> saveResisence(@RequestBody Residence residence, @AuthenticationPrincipal Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Optional<User> userOptional = userRepository.findByUsername(userDetails.getUsername());
        if(userOptional.isPresent()){
            residence.setUser(userOptional.get());
        }
        long newId = residenceService.saveResidence(residence);
        return new ResponseEntity<>(newId, HttpStatus.CREATED);
    }
    //Er moest nog een extra @Autowired worden toegevoegd van de UserRepository. Daarom bleef "userRepository" hier rood.

    @PutMapping(value = "/residences/{id}")
    public ResponseEntity<Object> updateResidence(@PathVariable("id") int id, @RequestBody Residence residence) {
        residenceService.updateResidence(id, residence); //clientUpdate bestaat dan nog niet-->In de clientService gaan maken
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //Het zou kunnen dat er zoals @postmapping data moet worden toegevoegd voor het renderen op de frondend
    //Nog eens specifiek op zoeken
    //-->IIG nog aanpassen lastname!
    @GetMapping(value = "/residences/lastname/{lastname}")
    public ResponseEntity<Object> getResidenceByLastName(@PathVariable("lastname") String lastName) {
        Residence residence = residenceService.getResidenceByLastName(lastName);
        return new ResponseEntity<>(residence, HttpStatus.OK);
    }
    //Let hier op "clientname" en "String clientName"
}

