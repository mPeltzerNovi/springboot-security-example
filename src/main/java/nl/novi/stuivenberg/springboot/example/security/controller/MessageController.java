package nl.novi.stuivenberg.springboot.example.security.controller;

import nl.novi.stuivenberg.springboot.example.security.domain.Message;
import nl.novi.stuivenberg.springboot.example.security.service.MessageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessageController {
    @Autowired
    private MessageService messageService;

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

    @PostMapping(value = "/messages")
    public ResponseEntity<Object> saveMessage(@RequestBody Message message) {
        long newId = messageService.saveMessage(message);
        return new ResponseEntity<>(newId, HttpStatus.CREATED);
    }

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
