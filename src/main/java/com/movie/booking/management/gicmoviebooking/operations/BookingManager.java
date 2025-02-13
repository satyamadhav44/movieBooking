package com.movie.booking.management.gicmoviebooking.operations;

import java.util.List;
import java.util.Scanner;

import static com.movie.booking.management.gicmoviebooking.GicMovieApplication.bookingHistory;
import static com.movie.booking.management.gicmoviebooking.GicMovieApplication.count;
import static com.movie.booking.management.gicmoviebooking.constants.MessagePrompts.ErrorWarningMsg.validFormat;
import static com.movie.booking.management.gicmoviebooking.constants.MessagePrompts.ErrorWarningMsg.validInput;
import static com.movie.booking.management.gicmoviebooking.constants.MessagePrompts.*;

public class BookingManager {
    private final ISeatAllocator seatAllocator;

    public BookingManager(ISeatAllocator seatAllocator) {
        this.seatAllocator = seatAllocator;

    }

    public void bookTickets(Cinema cinema, Scanner scanner) {

        while (true) {
            try {
                System.out.println(bookTicketMsg);
                String seatCounts = scanner.nextLine();
                if (!seatCounts.trim().isEmpty()) {
                    int seatReserveCount = Integer.parseInt(seatCounts.trim());
                    if (seatReserveCount > cinema.getAvailableSeats()) {
                        System.out.println("Sorry, there are only " + cinema.getAvailableSeats() + " seats available");
                        System.out.println();
                        continue;
                    }
                    System.out.println(susscessMsg + seatReserveCount + " " + cinema.getTitle() + " tickets");
                    String newBookingId = generateBookingNum();
                    System.out.println();
                    System.out.println(bookingId + newBookingId);
                    System.out.println(selectedSeat);
                    System.out.println();
                    System.out.printf("%3s", screen);
                    System.out.println();
                    List<String> calculatedSeated = seatAllocator.allocateSeats(cinema, seatReserveCount, null);
                    cinema.displaySeatingMap();
                    System.out.println();
                    while (true) {
                        System.out.println(confirmSelectionPrompt);
                        String selSeat = scanner.nextLine();
                        if (!selSeat.trim().isEmpty()) {
                            seatAllocator.rollbackAllocation(cinema.getSeatingMap(), calculatedSeated);
                            calculatedSeated = seatAllocator.allocateSeats(cinema, seatReserveCount, selSeat);
                            System.out.println();
                            System.out.println(bookingId + newBookingId);
                            System.out.println(selectedSeat);
                            cinema.displaySeatingMap();
                            System.out.println();
                        } else {

                            System.out.println(bookingId + newBookingId + " confirmed");
                            seatAllocator.confirmAllocation(cinema, calculatedSeated);
                            bookingHistory.put(newBookingId, calculatedSeated);
                            System.out.println();
                            break;
                        }
                    }
                }
            } catch (NumberFormatException ex) {
                System.err.println(validFormat);
            }

            break;
        }
    }

    public void showBookingById(Cinema cinema, Scanner sc) {
        while (true) {
            System.out.println(bookingById);
            String id = sc.nextLine();
            if (!id.isEmpty()) {
                if (id.matches("^GIC[0-9]+")) {
                    if (bookingHistory.containsKey(id)) {
                        System.out.println(bookingId+id);
                        System.out.println(selectedSeat);
                        System.out.println();
                        System.out.printf("%s",screen);
                        System.out.println();
                        cinema.displaySeatingMap(id, bookingHistory.get(id));
                        System.out.println();
                    } else {
                        System.out.println(bookingId + id.trim() + " does not exists");
                    }
                } else {
                    System.err.println(validInput);
                    System.out.println();
                }
            } else {
                break;
            }
        }
    }

    private String generateBookingNum() {
        String prefix = "GIC";
        return prefix + String.format("%04d", count++);
    }
}
