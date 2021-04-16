package nl.novi.stuivenberg.springboot.example.security.repository;

import nl.novi.stuivenberg.springboot.example.security.domain.Avatar;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AvatarRepository extends JpaRepository<Avatar, Long> {

    Avatar findByLastNameIgnoreCase(String lastName);

}
