package nl.novi.stuivenberg.springboot.example.security.controller;



//Gespecificeerde imports
import nl.novi.stuivenberg.springboot.example.security.domain.Booking;
import nl.novi.stuivenberg.springboot.example.security.domain.User;
import nl.novi.stuivenberg.springboot.example.security.repository.UserRepository;
import nl.novi.stuivenberg.springboot.example.security.service.BookingService;

import nl.novi.stuivenberg.springboot.example.security.service.UserDetailsImpl;
import nl.novi.stuivenberg.springboot.example.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:3000")
//Bovenstaand kan je waarschijnlijk terugveranderen in dat sterretje verhaal.
//@RestController toegevoegd
@RestController
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @Autowired
    UserRepository userRepository;

    @GetMapping(value = "/bookings")
    public ResponseEntity<Object> getBookings() {
        List<Booking> bookings = bookingService.getAllBookings();
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    //Deze lijkt niet in de uitwerking te staan, wellicht later aangepast-->Deze is omvangrijker 01:10:27 (les 4)
    @GetMapping(value = "/bookings/{id}")
    public ResponseEntity<Object> getBookings(@PathVariable("id") long id) {
        Booking booking = bookingService.getBookingById(id);
        return new ResponseEntity<>(booking, HttpStatus.OK);
    }

    @DeleteMapping(value = "/bookings/{id}")
    public ResponseEntity<Object> deleteBooking(@PathVariable("id") long id) {
        bookingService.deleteBooking(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); //dit is de foutcode die hij geeft als het niet lukt

    }
    //Hier is nieuwe code toegevoegd met hulp van Frank 18 feb 20!!!!!
    @PostMapping(value = "/bookings")
    public ResponseEntity<Object> saveBooking(@RequestBody Booking booking, @AuthenticationPrincipal Authentication authentication) {

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Optional<User> userOptional = userRepository.findByUsername(userDetails.getUsername());
        if(userOptional.isPresent()){
            booking.setUser(userOptional.get());
        }
        long newId = bookingService.saveBooking(booking);
        return new ResponseEntity<>(newId, HttpStatus.CREATED);
    }

    @PutMapping(value = "/bookings/{id}")
    public ResponseEntity<Object> updateBooking(@PathVariable("id") int id, @RequestBody Booking booking) {
        bookingService.updateBooking(id, booking); //clientUpdate bestaat dan nog niet-->In de clientService gaan maken
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/bookings/arrival/{arrival}")
    public ResponseEntity<Object> getBookingByArrival(@PathVariable("arrival") String arrival) {
        Booking booking = bookingService.getBookingByArrival(arrival);
        return new ResponseEntity<>(booking, HttpStatus.OK);
    }

}
