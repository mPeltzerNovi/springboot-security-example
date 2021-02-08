package nl.novi.stuivenberg.springboot.example.security.service;

//specifieke import
import nl.novi.stuivenberg.springboot.example.security.domain.Residence;

//algemene import
import java.util.List;

//extra Nick imports erbij



public interface ResidenceService {

    List<Residence>getAllResidences();
    Residence getResidenceById(long id);
    //Dit komt allemaal overeen met wat we in de repository hebben gezegd
    //Omdat dit een interface is, is dit ook het enige wat hier hoeft te staan
    void deleteResidence(long id);
    long saveResidence(Residence residence);
    void updateResidence(long id, Residence residence); //die dan implementeren in ClientServiceImpl
    //Extra dingen

    Residence getResidenceByLastName(String lastName);
}
