package com.movie.booking.management.gicmoviebooking;

import com.movie.booking.management.gicmoviebooking.operations.BookingManager;
import com.movie.booking.management.gicmoviebooking.operations.Cinema;
import com.movie.booking.management.gicmoviebooking.operations.TicketBooking;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;



@ExtendWith(MockitoExtension.class)
public class GicMovieApplicationTest {

    @Mock
    private Cinema cinema;

    @Mock
    private BookingManager bookingManager;

    @InjectMocks
    private TicketBooking ticketBooking;

    @Test
    public void testProcessInput_ValidInput() {
        String input = "Movie 5 5\n1\n2\n\n3\n";
        Scanner scanner = new Scanner(input);
        GicMovieApplication.beginWorkFlow(scanner);

    }


    @Test
    public void testProcessInput_InvalidNumberFormat() {
        String input = "Movie five 5\nMovie 5 5\n3\n";
        Scanner scanner = new Scanner(input);
        GicMovieApplication.beginWorkFlow(scanner);
    }
}
