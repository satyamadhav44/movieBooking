package com.movie.booking.management.gicmoviebooking;

import com.movie.booking.management.gicmoviebooking.operations.BookingManager;
import com.movie.booking.management.gicmoviebooking.operations.Cinema;
import com.movie.booking.management.gicmoviebooking.operations.ISeatAllocator;
import com.movie.booking.management.gicmoviebooking.operations.TicketBooking;
import com.movie.booking.management.gicmoviebooking.operations.impl.SeatAllocator;

import java.util.*;

import static com.movie.booking.management.gicmoviebooking.constants.MessagePrompts.ErrorWarningMsg.*;
import static com.movie.booking.management.gicmoviebooking.constants.MessagePrompts.tileAndSeatMapping;

public class GicMovieApplication {
    public static Map<String, List<String>> bookingHistory = new HashMap<>();
    public static int count = 1;

    public static void main(String[] args) {
        boolean movieSelection = true;
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println(tileAndSeatMapping);
            int rowNumbers = 0;
            int seatNumbers = 0;
            String input = sc.nextLine();
            if (Objects.isNull(input) || input.trim().isEmpty()) {
                System.err.println(validInput);
                System.out.println();
                continue;
            }
            String[] eachInput = input.split("\\s");
            if (eachInput.length != 3) {
                {
                    System.err.println(validFormat);
                    System.out.println();
                    continue;
                }
            } else {
                try {
                    rowNumbers = Integer.parseInt(eachInput[1]);
                    seatNumbers = Integer.parseInt(eachInput[2]);
                    if (rowNumbers > 26 || seatNumbers > 50 || rowNumbers <= 0 || seatNumbers <= 0) {
                        System.err.println(allocWarning);
                        System.out.println();
                        continue;
                    }
                } catch (NumberFormatException ex) {
                    System.err.println(validFormat);
                    //System.out.println(tileAndSeatMapping);
                    continue;
                }
            }
            System.out.println();
            //System.out.println();
            //initialize cinema
            Cinema cinema = new Cinema(eachInput[0], rowNumbers, seatNumbers);// Initialize BookingManager with DefaultSeatAllocator
            ISeatAllocator seatAllocator = new SeatAllocator();
            BookingManager bookingManager = new BookingManager(seatAllocator);
            TicketBooking ticketBooking = new TicketBooking(bookingManager);
            ticketBooking.showMainMenu(cinema, sc);
            movieSelection = false;
        } while (movieSelection);
    }

}
