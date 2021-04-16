package nl.novi.stuivenberg.springboot.example.security;

import nl.novi.stuivenberg.springboot.example.security.domain.Booking;
import nl.novi.stuivenberg.springboot.example.security.repository.BookingRepository;
import nl.novi.stuivenberg.springboot.example.security.service.BookingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


@SpringBootTest()

public class NoviExamplesApplicationTests {

	@Autowired
	private BookingService bookingService;

	@MockBean
	private BookingRepository bookingRepository;

	@Mock
	Booking booking;

	@BeforeEach
	public void setUp() {

	}

	@Test
	public void testGetBookingByArrival() {
		booking = new Booking("31okt", "2nov", "lang weekend", "null");

		// Setup our mock repository
		Mockito
				.when(bookingRepository.findByArrivalIgnoreCase(booking.getArrival()))
				.thenReturn(booking);

		String arrival = "31okt";
		String expected = "31okt 2nov lang weekend null";

		// Execute the service call
		Booking found = bookingService.getBookingByArrival(arrival);

		// Assert the response
		assertEquals(expected, found.getBooking());
	}


	@Test
	void testGetBookingByArrivalNotFound() {
		String arrival = "14janXXX";

		// Setup our mock repository
		Mockito
				.doReturn(null).when(bookingRepository)
				.findByArrivalIgnoreCase(arrival);

		// Execute the service call
		Booking found = bookingService.getBookingByArrival(arrival);

		// Assert the response
		assertNull(found, "Widget should not be found");
	}

}
