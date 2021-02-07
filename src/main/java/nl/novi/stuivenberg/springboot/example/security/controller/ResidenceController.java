/*
package nl.novi.stuivenberg.springboot.example.security.controller;

//De imports die je specifiek moet maken
import nl.novi.stuivenberg.springboot.example.security.domain.Residence;
import nl.novi.stuivenberg.springboot.example.security.repository.ResidenceRepository;
import nl.novi.stuivenberg.springboot.example.security.service.ResidenceService;

//Overige imports die je kan kopieren
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ResidenceController {

    @Autowired
    private ResidenceService residenceService;

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

    @DeleteMapping(value = "/residences/{id}")
    public ResponseEntity<Object> deleteResidence(@PathVariable("id") long id) {
        residenceService.deleteResidence(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); //dit is de foutcode die hij geeft als het niet lukt

    }

    @PostMapping(value = "/residences")
    public ResponseEntity<Object> saveResidence(@RequestBody Residence residence) {
        long newId = residenceService.saveResidence(residence);
        return new ResponseEntity<>(newId, HttpStatus.CREATED);
    }

    @PutMapping(value = "/residences/{id}")
    public ResponseEntity<Object> updateResidence(@PathVariable("id") int id, @RequestBody Residence residence) {
        residenceService.updateResidence(id, residence); //clientUpdate bestaat dan nog niet-->In de clientService gaan maken
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/residences/lastname/{lastname}")
    public ResponseEntity<Object> getResidenceByLastName(@PathVariable("lastname") String lastName) {
        Residence residence = residenceService.getResidenceByLastName(lastName);
        return new ResponseEntity<>(residence, HttpStatus.OK);
    }
}
*/
