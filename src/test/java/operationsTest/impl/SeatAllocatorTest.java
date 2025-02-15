package operationsTest.impl;

import com.movie.booking.management.gicmoviebooking.operations.Cinema;
import com.movie.booking.management.gicmoviebooking.operations.impl.SeatAllocator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SeatAllocatorTest {

    @Mock
    private Cinema cinema;

    @InjectMocks
    private SeatAllocator seatAllocator;

    private char[][] seatingMap;

    @BeforeEach
    public void setUp(){

        seatingMap = new char[][] {
                { '.', '.', '.', '.', '.' },
                { '.', '.', '.', '.', '.' },
                { '.', '.', '.', '.', '.' }
        };
    }


    @Test
    public void testAllocateSeatsDefault() {


        when(cinema.getSeatingMap()).thenReturn(seatingMap);
        List<String> allocatedSeats = seatAllocator.allocateSeatsDefault(cinema, 3);
        assertEquals(Arrays.asList("A2", "A3", "A4"), allocatedSeats);
    }

    @Test
    public void testAllocateSeatsFromCustomPosition() {


        when(cinema.getSeatingMap()).thenReturn(seatingMap);
        String customPosition = "B03";
        List<String> allocatedSeats = seatAllocator.allocateSeatsFromCustomPosition(cinema, 2, customPosition);
        assertEquals(Arrays.asList("B3", "B4"), allocatedSeats);
        verify(cinema).getSeatingMap();
    }

    @Test
    public void testRollbackAllocation() {
        List<String> allocatedSeats = Arrays.asList("A1", "A2", "A3");
        seatAllocator.rollbackAllocation(seatingMap, allocatedSeats);

        assertEquals('.', seatingMap[0][0]);
        assertEquals('.', seatingMap[0][1]);
        assertEquals('.', seatingMap[0][2]);
    }

    @Test
    public void testConfirmAllocation() {
        when(cinema.getSeatingMap()).thenReturn(seatingMap);
        List<String> allocatedSeats = Arrays.asList("A1", "A2", "A3");
        seatAllocator.confirmAllocation(cinema, allocatedSeats);

        assertEquals('#', seatingMap[0][0]);
        assertEquals('#', seatingMap[0][1]);
        assertEquals('#', seatingMap[0][2]);
        verify(cinema).updateAvailableSeats(allocatedSeats.size());
    }

}
