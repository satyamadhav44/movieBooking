package com.movie.booking.management.gicmoviebooking.operations;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class Cinema {
    private final String title;
    private final int rows;
    private final int seatsPerRow;
    private final char[][] seatingMap;
    private int availableSeats;


    public Cinema(String title, int rows, int seatsPerRow) {
        this.title = title;
        this.rows = rows;
        this.seatsPerRow = seatsPerRow;
        this.seatingMap = new char[rows][seatsPerRow];
        this.availableSeats = rows * seatsPerRow;
        // Initialize seating map with 'O' (available seats)
        for (int i = 0; i < rows; i++) {
            Arrays.fill(seatingMap[i], '.');
        }
    }

    public void updateAvailableSeats(int count) {
        availableSeats -= count;
    }

    public void displaySeatingMap() {

        for (int i = 1; i <= seatsPerRow; i++) {
            System.out.printf("%2s ", "--");
        }
        System.out.println();

        for (int i = rows - 1; i >= 0; i--) {
            System.out.print((char) ('A' + i) + " ");
            for (int j = 0; j < seatsPerRow; j++) {
                System.out.print(seatingMap[i][j] + "  ");
            }
            System.out.println();
        }
        System.out.print(" ");
        for (int i = 1; i <= seatsPerRow; i++) {
            System.out.printf("%2d ", i);
        }
        System.out.println();
    }

    public void displaySeatingMap(String id, List<String> coordinates) {
        coordinates.forEach(seat -> {
            int row = seat.charAt(0) - 'A';
            int col = Integer.parseInt(seat.substring(1)) - 1;
            seatingMap[row][col] = '0';
        });

        for (int i = 1; i <= seatsPerRow; i++) {
            System.out.printf("%2s ", "--");
        }
        System.out.println();

        for (int i = rows - 1; i >= 0; i--) {
            System.out.print((char) ('A' + i) + " ");
            for (int j = 0; j < seatsPerRow; j++) {
                System.out.print(seatingMap[i][j] + "  ");
            }
            System.out.println();
        }
        System.out.print(" ");
        for (int i = 1; i <= seatsPerRow; i++) {
            System.out.printf("%2d ", i);
        }
        System.out.println();
        coordinates.forEach(seat -> {
            int row = seat.charAt(0) - 'A';
            int col = Integer.parseInt(seat.substring(1)) - 1;
            seatingMap[row][col] = '#';
        });
    }
}
