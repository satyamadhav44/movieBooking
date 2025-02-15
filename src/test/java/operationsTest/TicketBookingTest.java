package operationsTest;

import com.movie.booking.management.gicmoviebooking.operations.BookingManager;
import com.movie.booking.management.gicmoviebooking.operations.Cinema;
import com.movie.booking.management.gicmoviebooking.operations.TicketBooking;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Scanner;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TicketBookingTest {

    @Mock
    private BookingManager bookingManager;

    @Mock
    private Cinema cinema;

    @InjectMocks
    private TicketBooking ticketBooking;

    @BeforeEach
    public void setUp() {
        // Set up mock behaviors
       /* when(cinema.getTitle()).thenReturn("Movie Title");
        when(cinema.getAvailableSeats()).thenReturn(100);*/
    }

    @Test
    public void testShowMainMenu_BookTickets() {

        String input = "1\n3\n";
        Scanner scanner = new Scanner(input);
        when(cinema.getTitle()).thenReturn("Movie Title");
        when(cinema.getAvailableSeats()).thenReturn(100);
        ticketBooking.showMainMenu(cinema, scanner);
        verify(bookingManager,times(1)).bookTickets(cinema, scanner);
    }

    @Test
    public void testShowMainMenu_ShowBookingById() {
        String input = "2\nGIC0001\n3\n";
        when(cinema.getTitle()).thenReturn("Movie Title");
        when(cinema.getAvailableSeats()).thenReturn(100);
        Scanner scanner = new Scanner(input);

        ticketBooking.showMainMenu(cinema, scanner);

        verify(bookingManager).showBookingById(cinema, scanner);
    }

    @Test
    public void testShowMainMenu_Exit() {
        String input = "3\n";
        Scanner scanner = new Scanner(input);
        when(cinema.getTitle()).thenReturn("Movie Title");
        when(cinema.getAvailableSeats()).thenReturn(100);

        ticketBooking.showMainMenu(cinema, scanner);

        verifyNoInteractions(bookingManager);
    }

    @Test
    public void testShowMainMenu_InvalidOption() {
        String input = "4\n3\n";
        Scanner scanner = new Scanner(input);
        when(cinema.getTitle()).thenReturn("Movie Title");
        when(cinema.getAvailableSeats()).thenReturn(100);

        ticketBooking.showMainMenu(cinema, scanner);

        verifyNoInteractions(bookingManager);
    }

    @Test
    public void testShowMainMenu_EmptyInput() {
        String input = "\n3\n";
        Scanner scanner = new Scanner(input);
        when(cinema.getTitle()).thenReturn("Movie Title");
        when(cinema.getAvailableSeats()).thenReturn(100);

        ticketBooking.showMainMenu(cinema, scanner);

        verifyNoInteractions(bookingManager);
    }

    @Test
    public void testShowMainMenu_InvalidFormat() {
        String input = "abc\n3\n";
        Scanner scanner = new Scanner(input);
        when(cinema.getTitle()).thenReturn("Movie Title");
        when(cinema.getAvailableSeats()).thenReturn(100);

        ticketBooking.showMainMenu(cinema, scanner);

        verifyNoInteractions(bookingManager);
    }

    @Test
    public void testShowMainMenu_InsufficientSeats() {

        String input = "1\n10\n4\n\n3\n";
        Scanner scanner = new Scanner(input);
        when(cinema.getTitle()).thenReturn("Movie Title");
        when(cinema.getAvailableSeats()).thenReturn(5);

        ticketBooking.showMainMenu(cinema, scanner);

        verify(bookingManager, times(1)).bookTickets(cinema, scanner);
    }
}
