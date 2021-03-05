package nl.novi.stuivenberg.springboot.example.security.controller;

import nl.novi.stuivenberg.springboot.example.security.domain.Message;
import nl.novi.stuivenberg.springboot.example.security.service.MessageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//Deze ook, ongebruikt in booking
import nl.novi.stuivenberg.springboot.example.security.service.UserDetailsImpl;
import nl.novi.stuivenberg.springboot.example.security.service.UserService;

//Toevoeging zoals in bookings
import nl.novi.stuivenberg.springboot.example.security.domain.User;
import nl.novi.stuivenberg.springboot.example.security.repository.UserRepository;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
//@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class MessageController {
    @Autowired
    private MessageService messageService;

    @Autowired
    UserRepository userRepository;

    @GetMapping(value = "/messages")
    public ResponseEntity<Object> getMessages() {
        List<Message> messages = messageService.getAllMessages();
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    //Deze lijkt niet in de uitwerking te staan, wellicht later aangepast-->Deze is omvangrijker 01:10:27 (les 4)
    @GetMapping(value = "/messages/{id}")
    public ResponseEntity<Object> getMessages(@PathVariable("id") long id) {
        Message message = messageService.getMessageById(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping(value = "/messages/{id}")
    public ResponseEntity<Object> deleteMessage(@PathVariable("id") long id) {
        messageService.deleteMessage(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); //dit is de foutcode die hij geeft als het niet lukt

    }

    //Deze moet aangepast worden met de code van Frank!!!!
    @PostMapping(value = "/messages")
    public ResponseEntity<Object> saveMessage(@RequestBody Message message, @AuthenticationPrincipal Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Optional<User> userOptional = userRepository.findByUsername(userDetails.getUsername());
        if(userOptional.isPresent()){
            message.setUser(userOptional.get());
        }
        //dit kon blijven staan
        long newId = messageService.saveMessage(message);
        return new ResponseEntity<>(newId, HttpStatus.CREATED);
    }
    //Het rood van ".setUser" ging weg na aanmaken getters en setters van "User" in "Message"

    @PutMapping(value = "/messages/{id}")
    public ResponseEntity<Object> updateMessage(@PathVariable("id") int id, @RequestBody Message message) {
        messageService.updateMessage(id, message); //clientUpdate bestaat dan nog niet-->In de clientService gaan maken
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/messages/clientname/{clientname}")
    public ResponseEntity<Object> getMessageByClientName(@PathVariable("clientname") String clientName) {
        Message message = messageService.getMessageByClientName(clientName);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    //Let hier op "clientname" en "String clientName"
}
