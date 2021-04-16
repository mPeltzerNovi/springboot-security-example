package nl.novi.stuivenberg.springboot.example.security.service;

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
    public void deleteBooking(long id) {
        if (bookingRepository.existsById(id)) {
            bookingRepository.deleteById(id);
        }
        else {
            throw new RecordNotFoundException();
            //evt nog recordNotFoundException toevoegen
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

                Booking existingBooking = bookingRepository.findById(id).orElse(null);
                existingBooking.setArrival(booking.getArrival());
                existingBooking.setDeparture(booking.getDeparture());
                existingBooking.setComment(booking.getComment());
                existingBooking.setBaseImage(booking.getBaseImage());
                bookingRepository.save(existingBooking);
            }
            catch (Exception ex) {
                throw new DatabaseErrorException();
            }

        }
        else {
            throw new RecordNotFoundException();
        }

    }

    @Override
    public Booking getBookingByArrival(String arrival) {
        try {
            return bookingRepository.findByArrivalIgnoreCase(arrival);
        } catch (Exception ex) {
            throw new RecordNotFoundException();
        }
    }

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
