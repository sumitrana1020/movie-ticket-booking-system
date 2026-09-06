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
    static Cinema cinema = new Cinema("PVR");
    static BookingService bookingService = new BookingService();
    static Scanner sc = new Scanner(System.in);
    static List<Booking> allBookings = new ArrayList<>();

    public static void main(String[] args) {
        setupData();

        while (true) {
            System.out.println();
            System.out.println("===== MOVIE TICKET BOOKING =====");
            System.out.println("1. Movies  2. Book  3. Cancel  4. My tickets   0. Exit");
            System.out.print("Choose: ");
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1":
                    cinema.printMovies();
                    break;
                case "2":
                    handleBooking();
                    break;
                case "3":
                    handleCancel();
                    break;
                case "4":
                    printMyTickets();
                    break;
                case "0":
                    System.out.println("Goodbye!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void handleBooking() {
        cinema.printMovies();
        System.out.print("\nChoose movie: ");
        int movieIndex = readInt();
        Movie movie = cinema.getMovieByIndex(movieIndex);
        if (movie == null) {
            System.out.println("Invalid movie choice.");
            return;
        }

        cinema.printShowsForMovie(movie);
        System.out.print("Choose show: ");
        int showIndex = readInt();
        Show show = cinema.getShowByIndex(movie, showIndex);
        if (show == null) {
            System.out.println("Invalid show choice.");
            return;
        }

        show.printSeatLayout();
        System.out.print("\nSeats (e.g. A1,B2): ");
        String input = sc.nextLine();

        List<ShowSeat> selected = new ArrayList<>();
        boolean allValid = true;
        for (String seatNo : input.split(",")) {
            ShowSeat ss = show.getShowSeat(seatNo.trim());
            if (ss == null) {
                System.out.println("Invalid seat number: " + seatNo.trim());
                allValid = false;
            } else {
                selected.add(ss);
            }
        }
        if (!allValid || selected.isEmpty()) {
            System.out.println("Booking cancelled due to invalid seat input.");
            return;
        }

        Customer customer = new Customer("Sumit", "9999999999");
        Booking booking = bookingService.createBooking(customer, show, selected);
        if (booking == null) {
            System.out.println("Booking failed — a seat may already be booked.");
            return;
        }

        for (ShowSeat ss : selected) {
            System.out.println("  " + ss.getSeat().getNumber() + " " + ss.getSeat().getType() + " Rs." + ss.getSeat().getPrice());
        }
        System.out.println("  TOTAL           Rs." + booking.getTotalAmount());

        System.out.print("\nPay by: 1.UPI  2.Card  3.Cash > ");
        int payChoice = readInt();
        Payment payment;
        switch (payChoice) {
            case 1:
                payment = new UpiPayment(booking.getTotalAmount(), "sumit@upi");
                break;
            case 2:
                payment = new CardPayment(booking.getTotalAmount(), "1234567890123456");
                break;
            case 3:
                payment = new CashPayment(booking.getTotalAmount());
                break;
            default:
                System.out.println("Invalid payment method.");
                return;
        }

        boolean success = bookingService.makePayment(booking, payment);
        if (success) {
            allBookings.add(booking);
        }
    }

    private static void handleCancel() {
        if (allBookings.isEmpty()) {
            System.out.println("No bookings to cancel.");
            return;
        }
        printMyTickets();
        System.out.print("Enter booking ID to cancel: ");
        String id = sc.nextLine().trim();
        for (Booking b : allBookings) {
            if (b.getBookingId().equals(id)) {
                b.cancel();
                System.out.println("Booking " + id + " cancelled. Seats released.");
                return;
            }
        }
        System.out.println("Booking ID not found.");
    }

    private static void printMyTickets() {
        if (allBookings.isEmpty()) {
            System.out.println("No bookings yet.");
            return;
        }
        for (Booking b : allBookings) {
            System.out.println("  " + b.getBookingId() + " | " + b.getShow().getMovie().getTitle() + " | " + b.getStatus());
        }
    }

    private static int readInt() {
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void setupData() {
        Screen screen1 = new Screen(1);
        screen1.addSeat(new Seat("A1", SeatType.SILVER));
        screen1.addSeat(new Seat("A2", SeatType.SILVER));
        screen1.addSeat(new Seat("A3", SeatType.SILVER));
        screen1.addSeat(new Seat("A4", SeatType.SILVER));
        screen1.addSeat(new Seat("B1", SeatType.GOLD));
        screen1.addSeat(new Seat("B2", SeatType.GOLD));
        screen1.addSeat(new Seat("B3", SeatType.GOLD));
        screen1.addSeat(new Seat("C1", SeatType.PLATINUM));
        screen1.addSeat(new Seat("C2", SeatType.PLATINUM));
        cinema.addScreen(screen1);

        Movie movie1 = new Movie("3 Idiots", "Hindi", 170);
        Movie movie2 = new Movie("Interstellar", "English", 169);

        cinema.addShow(new Show("06:00 PM", movie1, screen1));
        cinema.addShow(new Show("09:00 PM", movie1, screen1));
    }
}