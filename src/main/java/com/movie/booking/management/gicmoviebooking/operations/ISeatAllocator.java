package com.movie.booking.management.gicmoviebooking.operations;

import java.util.List;

public interface ISeatAllocator {
    List<String> allocateSeats(Cinema cinema, int ticketCount, String customPosition);

    void rollbackAllocation(char[][] seatingMap, List<String> allocatedSeats);

    void confirmAllocation(Cinema cinema, List<String> allocatedSeats);
}
