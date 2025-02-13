package com.movie.booking.management.gicmoviebooking.constants;

public class MessagePrompts {

    public final static String welcomeMessage = "Welcome to GIC Cinemas";
    public final static String tileAndSeatMapping = "Please define movie title and seat mapping in [Title] [Row] [SeatPerRow] format:";
    public final static String bookingMsg = "[1] Book tickets for ";
    public final static String checkBookings = "[2] Check Booking";
    public final static String exitPrompt = "[3] Exit";
    public final static String selectionMsg = "Please enter your selection:";
    public final static String exitMsg = "Thank you for using GIC Cinemas system. Bye!";
    public final static String bookTicketMsg = "Enter number of tickets to book, or enter blank to go back to main menu";
    public final static String susscessMsg = "Successfully reserved ";
    public final static String bookingId = "Booking Id: ";
    public final static String selectedSeat = "Selected seats:";
    public final static String screen = " S C R E E N ";
    public final static String confirmSelectionPrompt = "Enter blank to accept seat selection, or enter new seating position: ";
    public final static String bookingById = "Enter booking id, or enter blank to go back to main menu: ";

    public static class ErrorWarningMsg {
        public final static String validInput = "Please provide valid input";
        public final static String validFormat = "Please provide valid input in the desired format";
        public final static String allocWarning = "maximum number of allowed rows is 26 and seats per column is 50, pls try again";
        public final static String optionSel = "Please select the valid option";
    }


}
