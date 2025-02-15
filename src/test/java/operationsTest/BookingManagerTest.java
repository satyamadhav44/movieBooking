package operationsTest;

import com.movie.booking.management.gicmoviebooking.operations.BookingManager;
import com.movie.booking.management.gicmoviebooking.operations.Cinema;
import com.movie.booking.management.gicmoviebooking.operations.ISeatAllocator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookingManagerTest {

    @Mock
    private ISeatAllocator seatAllocator;

    @Mock
    Cinema cinema;

    @InjectMocks
    private BookingManager bookingManager;


    @Test
    public void testBookTicketsWithValidInput() {

        String input = "5\n\n";
        Scanner scanner = new Scanner(input);
        List<String> allocatedSeats = Arrays.asList("A1", "A2", "A3", "A4", "A5");
        when(cinema.getAvailableSeats()).thenReturn(100);
        when(cinema.getTitle()).thenReturn("Movie Title");
        when(seatAllocator.allocateSeats(cinema, 5, null)).thenReturn(allocatedSeats);

        bookingManager.bookTickets(cinema, scanner);

        verify(seatAllocator).allocateSeats(cinema, 5, null);
        verify(seatAllocator).confirmAllocation(cinema, allocatedSeats);
    }

    @Test
    public void testBookTicketsWithInvalidFormat() {
        String input = "abc\n\n";
        Scanner scanner = new Scanner(input);

        bookingManager.bookTickets(cinema, scanner);

        // Verify that no allocation methods are called when input is invalid
        verify(seatAllocator, never()).allocateSeats(any(Cinema.class), anyInt(), anyString());
    }

    @Test
    public void testBookTicketsWithInvalidInput() {
        String input = "105\n\n";
        Scanner scanner = new Scanner(input);

        when(cinema.getAvailableSeats()).thenReturn(100);
        bookingManager.bookTickets(cinema, scanner);

        verify(cinema, times(2)).getAvailableSeats();  // This is called twice due to the loop
        verify(seatAllocator, never()).allocateSeats(any(Cinema.class), anyInt(), anyString());
    }
}

