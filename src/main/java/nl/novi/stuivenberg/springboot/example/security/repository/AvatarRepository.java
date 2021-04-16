package nl.novi.stuivenberg.springboot.example.security.repository;

//Specifieke import
import nl.novi.stuivenberg.springboot.example.security.domain.Avatar;
import org.springframework.data.jpa.repository.JpaRepository;

//Niet specifieke imports


public interface AvatarRepository extends JpaRepository<Avatar, Long> {

    //Waarschijnlijk moet ik hier toch een "getAvatarImageFOrSite" oid maken.

    Avatar findByLastNameIgnoreCase(String lastName);
    //Residence findByResidenceNr(long residenceNr);
    //Door R 17 liep het vast. Dit nu uit gecommentarieerd.
}
