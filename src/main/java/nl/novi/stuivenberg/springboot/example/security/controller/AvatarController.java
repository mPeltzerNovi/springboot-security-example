package nl.novi.stuivenberg.springboot.example.security.controller;

//


//

//De imports die je specifiek moet maken
import nl.novi.stuivenberg.springboot.example.security.domain.Avatar;

import nl.novi.stuivenberg.springboot.example.security.domain.User;
import nl.novi.stuivenberg.springboot.example.security.repository.UserRepository;
import nl.novi.stuivenberg.springboot.example.security.service.AvatarService;

//Overige imports die je kan kopieren
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//Deze ook, ongebruikt in booking en message

//toegevoegd door kopieren
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;


import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
//@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class AvatarController {

    @Autowired
    private AvatarService avatarService;

    //Er moest noeg een extra @Autowired worden toegevoegd voor de UserRepository
    @Autowired
    UserRepository userRepository;

    @GetMapping(value = "/avatars")
    public ResponseEntity<Object> getAvatars() {
        List<Avatar> avatars = avatarService.getAllAvatars();
        return new ResponseEntity<>(avatars, HttpStatus.OK);
    }

    //Deze lijkt niet in de uitwerking te staan, wellicht later aangepast-->Deze is omvangrijker 01:10:27 (les 4)
    @GetMapping(value = "/avatars/{id}")
    public ResponseEntity<Object> getAvatars(@PathVariable("id") long id) {
        Avatar avatar = avatarService.getAvatarById(id);
        return new ResponseEntity<>(avatar, HttpStatus.OK);
    }

    //Mogelijk later net zo aanpassen als in @PostMapping
    @DeleteMapping(value = "/avatars/{id}")
    public ResponseEntity<Object> deleteAvatar(@PathVariable("id") long id) {
        avatarService.deleteAvatar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); //dit is de foutcode die hij geeft als het niet lukt

    }

    //Aangepast zoals mesage en booking dmv code Frank
    //zal deels aankleuren tot de extra getters en setters zijn aangemaakt
    @PostMapping(value = "/avatars")
    public ResponseEntity<Object> saveAvatar(@RequestBody Avatar avatar, @AuthenticationPrincipal Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Optional<User> userOptional = userRepository.findByUsername(userDetails.getUsername());
        if(userOptional.isPresent()){
            avatar.setUser(userOptional.get());
        }
        long newId = avatarService.saveAvatar(avatar);
        return new ResponseEntity<>(newId, HttpStatus.CREATED);
    }
    //Er moest nog een extra @Autowired worden toegevoegd van de UserRepository. Daarom bleef "userRepository" hier rood.

    @PutMapping(value = "/avatars/{id}")
    public ResponseEntity<Object> updateAvatar(@PathVariable("id") int id, @RequestBody Avatar avatar) {
        avatarService.updateAvatar(id, avatar); //clientUpdate bestaat dan nog niet-->In de clientService gaan maken
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //Het zou kunnen dat er zoals @postmapping data moet worden toegevoegd voor het renderen op de frondend
    //Nog eens specifiek op zoeken
    //-->IIG nog aanpassen lastname!
    //LastName zo laten; "Waarschijnlijk willen we dit als eerste in fase 2 aan de avatar koppelen, daarom al gemaakt"
    @GetMapping(value = "/avatars/lastname/{lastname}")
    public ResponseEntity<Object> getAvatarByLastName(@PathVariable("lastname") String lastName) {
        Avatar avatar = avatarService.getAvatarByLastName(lastName);
        return new ResponseEntity<>(avatar, HttpStatus.OK);
    }
    //Let hier op "clientname" en "String clientName"
}

