package nl.novi.stuivenberg.springboot.example.security.service;

//specifieke import
import nl.novi.stuivenberg.springboot.example.security.domain.Booking;

//algemene import
import java.util.List;

public interface BookingService {

    List<Booking>getAllBookings();
    Booking getBookingById(long id);
    //Dit komt allemaal overeen met wat we in de repository hebben gezegd
    //Omdat dit een interface is, is dit ook het enige wat hier hoeft te staan
    void deleteBooking(long id);
    long saveBooking(Booking booking);
    void updateBooking(long id, Booking booking); //die dan implementeren in ClientServiceImpl
    //Extra dingen

    Booking getBookingByArrival(String arrival);
}
