package moviebooking.service;

import moviebooking.model.booking.Booking;
import moviebooking.model.booking.ShowSeat;

public class TicketPrinter {

    public void printTicket(Booking booking) {
        System.out.println("================ TICKET ================");
        System.out.println("Booking ID : " + booking.getBookingId());
        System.out.println("Movie      : " + booking.getShow().getMovie().getTitle());
        System.out.println("Screen     : " + booking.getShow().getScreen().getScreenNumber());
        System.out.println("Time       : " + booking.getShow().getStartTime());

        StringBuilder seatNumbers = new StringBuilder();
        for (ShowSeat ss : booking.getSelectedSeats()) {
            seatNumbers.append(ss.getSeat().getNumber()).append(" ");
        }
        System.out.println("Seats      : " + seatNumbers.toString().trim());
        System.out.println("Amount     : Rs." + booking.getTotalAmount());
        System.out.println("Status     : " + booking.getStatus());
        System.out.println("=========================================");
    }
}