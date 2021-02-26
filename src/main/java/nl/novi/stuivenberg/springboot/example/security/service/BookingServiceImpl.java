package nl.novi.stuivenberg.springboot.example.security.service;

//Specifieke imports

import nl.novi.stuivenberg.springboot.example.security.exception.DatabaseErrorException;
import nl.novi.stuivenberg.springboot.example.security.exception.RecordNotFoundException;
import nl.novi.stuivenberg.springboot.example.security.domain.Booking;
import nl.novi.stuivenberg.springboot.example.security.repository.BookingRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;




@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    BookingRepository bookingRepository;

    @Override

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking getBookingById(long id) {
        if(bookingRepository.existsById(id)) {
            return bookingRepository.findById(id).orElse(null);
        }
        else {
            throw new RecordNotFoundException();
        }
    }

    @Override
    public void deleteBooking(long id) { //deleteClient gaat dus praten met de clientRepository
        //Aanpassen-->Exception foutmelding invoeren:
        if (bookingRepository.existsById(id)) {
            bookingRepository.deleteById(id);
        }
        else {
            throw new RecordNotFoundException();
            //RuntimeException naar RecordNotFoundException na aanmaken exception map met inhoud!
        }

    }

    @Override
    public long saveBooking(Booking booking) {
        Booking newBooking = bookingRepository.save(booking);
        return newBooking.getId();
    }

    @Override
    public void updateBooking(long id, Booking booking) {
        if(bookingRepository.existsById(id)) {
            try {
                //het id moet worden opgehaald
                Booking existingBooking = bookingRepository.findById(id).orElse(null);
                existingBooking.setArrival(booking.getArrival());
                existingBooking.setDeparture(booking.getDeparture());
                existingBooking.setComment(booking.getComment());
                //voor baseImage
                existingBooking.setBaseImage(booking.getBaseImage());
                bookingRepository.save(existingBooking);
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
    public Booking getBookingByArrival(String arrival) {
        try {
            return bookingRepository.findByArrivalIgnoreCase(arrival);
        } catch (Exception ex) {
            throw new RecordNotFoundException();
        }
    }

    //Eerdere manier voor fout afvangen:
    public Booking getBookingByArrival2(String arrival) {
        Booking booking = bookingRepository.findByArrivalIgnoreCase(arrival);
        if(booking == null) {
            throw new RecordNotFoundException();
        }
        else {
            return booking;
        }
    }
}
