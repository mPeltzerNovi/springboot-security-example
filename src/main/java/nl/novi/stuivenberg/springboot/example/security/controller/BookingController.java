package nl.novi.stuivenberg.springboot.example.security.controller;



//Gespecificeerde imports
import nl.novi.stuivenberg.springboot.example.security.domain.Booking;
import nl.novi.stuivenberg.springboot.example.security.service.BookingService;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController toegevoegd
@RestController
public class BookingController {
    @Autowired
    private BookingService bookingService;

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

    @PostMapping(value = "/bookings")
    public ResponseEntity<Object> saveBooking(@RequestBody Booking booking) {
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
