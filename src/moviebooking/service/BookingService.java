package moviebooking.service;

import java.util.List;
import moviebooking.model.booking.Booking;
import moviebooking.model.booking.Show;
import moviebooking.model.booking.ShowSeat;
import moviebooking.model.entity.Customer;
import moviebooking.payment.Payment;

public class BookingService {
    private PriceCalculator calculator;
    private TicketPrinter printer;

    public BookingService() {
        this.calculator = new PriceCalculator();
        this.printer = new TicketPrinter();
    }

    public Booking createBooking(Customer customer, Show show, List<ShowSeat> seats) {
        for (ShowSeat ss : seats) {
            if (!ss.isAvailable()) {
                System.out.println("Seat " + ss.getSeat().getNumber() + " is already booked.");
                return null;
            }
        }
        double total = calculator.calculate(seats);
        return new Booking(show, customer, seats, total);
    }

    public boolean makePayment(Booking booking, Payment payment) {
        booking.setPayment(payment);
        boolean success = payment.pay(booking.getTotalAmount());
        if (success) {
            booking.confirm();
            printer.printTicket(booking);
        } else {
            booking.fail();
            System.out.println("Payment failed. Booking not confirmed.");
        }
        return success;
    }
}