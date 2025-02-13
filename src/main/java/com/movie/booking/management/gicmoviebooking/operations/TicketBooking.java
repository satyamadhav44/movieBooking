package com.movie.booking.management.gicmoviebooking.operations;

import java.util.Scanner;

import static com.movie.booking.management.gicmoviebooking.constants.MessagePrompts.ErrorWarningMsg.optionSel;
import static com.movie.booking.management.gicmoviebooking.constants.MessagePrompts.ErrorWarningMsg.validInput;
import static com.movie.booking.management.gicmoviebooking.constants.MessagePrompts.*;

public class TicketBooking {
    private final BookingManager bookingManager;

    public TicketBooking(BookingManager bookingManager) {
        this.bookingManager = bookingManager;
    }

    public void showMainMenu(Cinema cinema, Scanner scanner) {
        boolean opSelect = true;
        do {
            int sel = 0;
            System.out.println(welcomeMessage);
            System.out.println(bookingMsg + cinema.getTitle() + " (" + cinema.getAvailableSeats() + " seats available)");
            System.out.println(checkBookings);
            System.out.println(exitPrompt);
            System.out.println(selectionMsg);

            try {
                String selInput = scanner.nextLine().trim();
                if (!selInput.isEmpty()) {
                    sel = Integer.parseInt(selInput);
                    switch (sel) {
                        case 1: {
                            bookingManager.bookTickets(cinema, scanner);
                            break;
                        }
                        case 2: {
                            bookingManager.showBookingById(cinema, scanner);
                            break;
                        }
                        case 3: {
                            opSelect = false;
                            System.out.println(exitMsg);
                            break;
                        }
                        default: {
                            System.err.println(optionSel);
                            break;
                        }

                    }
                } else {
                    System.out.println(validInput);
                    System.out.println();
                }
            } catch (NumberFormatException ex) {
                System.out.println(validInput);
                System.out.println();
            }

        } while (opSelect);
    }
}
