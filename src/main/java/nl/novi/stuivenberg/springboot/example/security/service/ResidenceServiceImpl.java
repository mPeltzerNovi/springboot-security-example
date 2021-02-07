/*
package nl.novi.stuivenberg.springboot.example.security.service;

//Specifieke imports
import nl.novi.stuivenberg.springboot.example.security.exception.DatabaseErrorException;
import nl.novi.stuivenberg.springboot.example.security.exception.RecordNotFoundException;
import nl.novi.stuivenberg.springboot.example.security.domain.Residence;
import nl.novi.stuivenberg.springboot.example.security.repository.ResidenceRepository;

//nog 3 generieke imports
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

//Meer Nick imports




@Service
public class ResidenceServiceImpl implements ResidenceService {

    @Autowired
    ResidenceRepository residenceRepository;

    @Override

    public List<Residence> getAllResidences() {
        return residenceRepository.findAll();
    }

    @Override
    public Residence getResidenceById(long id) {
        if(residenceRepository.existsById(id)) {
            return residenceRepository.findById(id).orElse(null);
        }
        else {
            throw new RecordNotFoundException();
        }
    }

    @Override
    public void deleteResidence(long id) { //deleteClient gaat dus praten met de clientRepository
        //Aanpassen-->Exception foutmelding invoeren:
        if (residenceRepository.existsById(id)) {
            residenceRepository.deleteById(id);
        }
        else {
            throw new RecordNotFoundException();
            //RuntimeException naar RecordNotFoundException na aanmaken exception map met inhoud!
        }

    }

    @Override
    public long saveResidence(Residence residence) {
        Residence newResidence = residenceRepository.save(residence);
        return newResidence.getId();
    }

    @Override
    public void updateResidence(long id, Residence residence) {
        if(residenceRepository.existsById(id)) {
            try {
                //het id moet worden opgehaald
                Residence existingResidence = residenceRepository.findById(id).orElse(null);
                existingResidence.setFirstName(residence.getFirstName());
                existingResidence.setLastName(residence.getLastName());
                existingResidence.setClientNr(residence.getClientNr());
                residenceRepository.save(existingResidence);
            }
            catch (Exception ex) {
                throw new DatabaseErrorException();
            }
            //Hier zie je nu hij kan twee verschillende foutcodes teruggeven
            //afhankelijk van wat er gebeurd is!!!

        }
        else {
            throw new RecordNotFoundException();
        }

    }

    //Nu met try catch blok een exception maken.Er onder als 2 de eerdere manier
    @Override
    public Residence getResidenceByLastName(String lastName) {
        try {
            return residenceRepository.findByLastNameIgnoreCase(lastName);
        } catch (Exception ex) {
            throw new RecordNotFoundException();
        }
    }

    //Eerdere manier voor fout afvangen:
    public Residence getResidenceByLastName2(String lastName) {
        Residence residence = residenceRepository.findByLastNameIgnoreCase(lastName);
        if(residence == null) {
            throw new RecordNotFoundException();
        }
        else {
            return residence;
        }
    }
}
*/
