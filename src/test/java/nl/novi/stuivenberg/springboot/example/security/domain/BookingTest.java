package nl.novi.stuivenberg.springboot.example.security.domain;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookingTest {

    private Booking booking;

    @BeforeEach
    void setUp() {
        this.booking = new Booking("31 okt", "2 nov", "lang weekend", "null");
    }

    @Test
    void testGetBooking() {
        String expectedBooking = "31 okt 2 nov lang weekend null";
        String actualBooking = this.booking.getBooking();
        assertEquals(expectedBooking, actualBooking);
    }

    @Test
    void testGetStay() {
        String expectedStay = "31 okt 2 nov";
        String actualStay = this.booking.getStay();
        assertEquals(expectedStay, actualStay);
    }
}


