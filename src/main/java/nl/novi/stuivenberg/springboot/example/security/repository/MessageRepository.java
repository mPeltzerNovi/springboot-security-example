package nl.novi.stuivenberg.springboot.example.security.repository;

//Niet specifieke imports


import nl.novi.stuivenberg.springboot.example.security.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long>{

    Message findByClientNameIgnoreCase(String clientName);

}



