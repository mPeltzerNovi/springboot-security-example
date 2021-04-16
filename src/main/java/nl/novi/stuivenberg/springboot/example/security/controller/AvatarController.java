package nl.novi.stuivenberg.springboot.example.security.controller;

import nl.novi.stuivenberg.springboot.example.security.domain.Avatar;
import nl.novi.stuivenberg.springboot.example.security.domain.User;
import nl.novi.stuivenberg.springboot.example.security.repository.UserRepository;
import nl.novi.stuivenberg.springboot.example.security.service.AvatarService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;


import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)

@RestController
public class AvatarController {

    @Autowired
    private AvatarService avatarService;

    //@Autowired voor de koppeling aan User
    @Autowired
    UserRepository userRepository;

    @GetMapping(value = "/avatars")
    public ResponseEntity<Object> getAvatars() {
        List<Avatar> avatars = avatarService.getAllAvatars();
        return new ResponseEntity<>(avatars, HttpStatus.OK);
    }

    @GetMapping(value = "/avatars/{id}")
    public ResponseEntity<Object> getAvatars(@PathVariable("id") long id) {
        Avatar avatar = avatarService.getAvatarById(id);
        return new ResponseEntity<>(avatar, HttpStatus.OK);
    }

    @DeleteMapping(value = "/avatars/{id}")
    public ResponseEntity<Object> deleteAvatar(@PathVariable("id") long id) {
        avatarService.deleteAvatar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); //dit is de foutcode die hij geeft als het niet lukt

    }

    //Combineert User en Avatar daarom ook extra @Autowired
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

    @PutMapping(value = "/avatars/{id}")
    public ResponseEntity<Object> updateAvatar(@PathVariable("id") int id, @RequestBody Avatar avatar) {
        avatarService.updateAvatar(id, avatar);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/avatars/lastname/{lastname}")
    public ResponseEntity<Object> getAvatarByLastName(@PathVariable("lastname") String lastName) {
        Avatar avatar = avatarService.getAvatarByLastName(lastName);
        return new ResponseEntity<>(avatar, HttpStatus.OK);
    }

}

