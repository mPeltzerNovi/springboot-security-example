package nl.novi.stuivenberg.springboot.example.security.repository;

//Niet specifieke imports
import nl.novi.stuivenberg.springboot.example.security.domain.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    Booking findByArrivalIgnoreCase(String arrival);

}



