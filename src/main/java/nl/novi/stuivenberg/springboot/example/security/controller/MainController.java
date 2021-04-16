package nl.novi.stuivenberg.springboot.example.security.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)

@RestController
public class MainController {

    @GetMapping(value = "/getCustomers")
    public ResponseEntity<Object> getCustomers() {
        return new ResponseEntity<>("Hello from demo_spring_jpa", HttpStatus.OK);
    }
}
