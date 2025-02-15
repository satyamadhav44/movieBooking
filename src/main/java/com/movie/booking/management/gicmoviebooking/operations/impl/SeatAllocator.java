package com.movie.booking.management.gicmoviebooking.operations.impl;

import com.movie.booking.management.gicmoviebooking.operations.Cinema;
import com.movie.booking.management.gicmoviebooking.operations.ISeatAllocator;

import java.util.ArrayList;
import java.util.List;

public class SeatAllocator implements ISeatAllocator {

    @Override
    public List<String> allocateSeats(Cinema cinema, int ticketCount, String customPosition) {
        if (customPosition != null) {
            return allocateSeatsFromCustomPosition(cinema, ticketCount, customPosition);
        }
        return allocateSeatsDefault(cinema, ticketCount);
    }

    public List<String> allocateSeatsDefault(Cinema cinema, int ticketCount) {
        char[][] seatingMap = cinema.getSeatingMap();
        List<String> allocatedSeats = new ArrayList<>();
        int remainingTickets = ticketCount;

        for (int row = 0; row < seatingMap.length && remainingTickets > 0; row++) {
            int middleCol = Math.abs(seatingMap[row].length / 2 - remainingTickets / 2);
            allocatedSeats.addAll(fillSeatsInRow(cinema, row, middleCol, remainingTickets));
            remainingTickets = ticketCount - allocatedSeats.size();
        }

        return allocatedSeats;
    }

    public List<String> allocateSeatsFromCustomPosition(Cinema cinema, int ticketCount, String customPosition) {
        List<String> allocatedSeats = new ArrayList<>();
        int remainingTickets = ticketCount;

        int startRow = customPosition.charAt(0) - 'A';
        int startCol = Integer.parseInt(customPosition.substring(1)) - 1;

        // Allocate seats from custom position
        for (int row = startRow; row >= 0 && remainingTickets > 0; row++) {
            int col = (row == startRow) ? startCol : 0;
            allocatedSeats.addAll(fillSeatsInRow(cinema, row, col, remainingTickets));
            remainingTickets = ticketCount - allocatedSeats.size();
        }

        // If seats are still needed, use default allocation for remaining
        if (remainingTickets > 0) {
            allocatedSeats.addAll(allocateSeatsDefault(cinema, remainingTickets));
        }

        return allocatedSeats;
    }

    private List<String> fillSeatsInRow(Cinema cinema, int row, int startCol, int ticketsNeeded) {
        char[][] seatingMap = cinema.getSeatingMap();
        List<String> allocatedSeats = new ArrayList<>();

        for (int col = startCol; col < seatingMap[row].length && allocatedSeats.size() < ticketsNeeded; col++) {
            if (seatingMap[row][col] == '.') {
                seatingMap[row][col] = '0';
                allocatedSeats.add((char) ('A' + row) + String.valueOf(col + 1));
            }
        }

        return allocatedSeats;
    }

    @Override
    public void rollbackAllocation(char[][] seatingMap, List<String> allocatedSeats) {
        allocatedSeats.forEach(seat -> {
            int row = seat.charAt(0) - 'A';
            int col = Integer.parseInt(seat.substring(1)) - 1;
            seatingMap[row][col] = '.'; // Reset seat to available
        });
    }

    @Override
    public void confirmAllocation(Cinema cinema, List<String> allocatedSeats) {
        char[][] seatingMap = cinema.getSeatingMap();
        allocatedSeats.forEach(seat -> {
            int row = seat.charAt(0) - 'A';
            int col = Integer.parseInt(seat.substring(1)) - 1;
            seatingMap[row][col] = '#'; // confirm seats
        });
        cinema.updateAvailableSeats(allocatedSeats.size());
    }

}
