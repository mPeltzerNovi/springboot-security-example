package nl.novi.stuivenberg.springboot.example.security.service;

import nl.novi.stuivenberg.springboot.example.security.domain.Booking;

import java.util.List;


public interface BookingService {

    List<Booking>getAllBookings();
    Booking getBookingById(long id);
    void deleteBooking(long id);
    long saveBooking(Booking booking);
    void updateBooking(long id, Booking booking);

    Booking getBookingByArrival(String arrival);
}
