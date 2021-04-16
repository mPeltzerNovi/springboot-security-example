package nl.novi.stuivenberg.springboot.example.security.controller;

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
@CrossOrigin(origins = "*", maxAge = 3600)

@RestController
public class BookingController {

    @Autowired
    private BookingService bookingService;

    //@Autowired voor User
    @Autowired
    UserRepository userRepository;

    @GetMapping(value = "/bookings")
    public ResponseEntity<Object> getBookings() {
        List<Booking> bookings = bookingService.getAllBookings();
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    @GetMapping(value = "/bookings/{id}")
    public ResponseEntity<Object> getBookings(@PathVariable("id") long id) {
        Booking booking = bookingService.getBookingById(id);
        return new ResponseEntity<>(booking, HttpStatus.OK);
    }

    @DeleteMapping(value = "/bookings/{id}")
    public ResponseEntity<Object> deleteBooking(@PathVariable("id") long id) {
        bookingService.deleteBooking(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    //Gebruik User en Booking
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
        bookingService.updateBooking(id, booking);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/bookings/arrival/{arrival}")
    public ResponseEntity<Object> getBookingByArrival(@PathVariable("arrival") String arrival) {
        Booking booking = bookingService.getBookingByArrival(arrival);
        return new ResponseEntity<>(booking, HttpStatus.OK);
    }

}
