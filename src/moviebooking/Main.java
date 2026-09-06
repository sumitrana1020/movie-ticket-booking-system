package moviebooking;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import moviebooking.model.booking.Booking;
import moviebooking.model.booking.Show;
import moviebooking.model.booking.ShowSeat;
import moviebooking.model.entity.*;
import moviebooking.model.enums.SeatType;
import moviebooking.payment.*;
import moviebooking.service.BookingService;

public class Main {
    public static void main(String[] args) {
        Cinema cinema = new Cinema("PVR");
        Screen screen1 = new Screen(1);
        screen1.addSeat(new Seat("A1", SeatType.SILVER));
        screen1.addSeat(new Seat("A2", SeatType.SILVER));
        screen1.addSeat(new Seat("B1", SeatType.GOLD));
        cinema.addScreen(screen1);

        Movie movie = new Movie("3 Idiots", "Hindi", 170);
        Show show = new Show("06:00 PM", movie, screen1);
        cinema.addShow(show);

        BookingService bookingService = new BookingService();
        Scanner sc = new Scanner(System.in);

        System.out.println("Choose seats (e.g. A1,B1): ");
        String input = sc.nextLine();
        List<ShowSeat> selected = new ArrayList<>();
        for (String seatNo : input.split(",")) {
            selected.add(show.getShowSeat(seatNo.trim()));
        }

        Customer customer = new Customer("Sumit", "9999999999");
        Booking booking = bookingService.createBooking(customer, show, selected);

        if (booking != null) {
            Payment payment = new UpiPayment(booking.getTotalAmount(), "sumit@upi");
            bookingService.makePayment(booking, payment);
        }

        sc.close();
    }
}