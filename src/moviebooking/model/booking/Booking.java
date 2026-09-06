package moviebooking.model.booking;

import java.util.List;
import moviebooking.model.entity.Customer;
import moviebooking.model.enums.BookingStatus;
import moviebooking.payment.Payment;

public class Booking {
    private static int nextId = 1000;

    private String bookingId;
    private Show show;
    private Customer customer;
    private List<ShowSeat> selectedSeats;
    private double totalAmount;
    private BookingStatus status;
    private Payment payment;

    public Booking(Show show, Customer customer, List<ShowSeat> selectedSeats, double totalAmount) {
        this.bookingId = "BK" + (++nextId);
        this.show = show;
        this.customer = customer;
        this.selectedSeats = selectedSeats;
        this.totalAmount = totalAmount;
        this.status = BookingStatus.PENDING;
    }

    public String getBookingId() {
        return bookingId;
    }

    public Show getShow() {
        return show;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<ShowSeat> getSelectedSeats() {
        return selectedSeats;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public void confirm() {
        for (ShowSeat ss : selectedSeats) {
            ss.book();
        }
        this.status = BookingStatus.CONFIRMED;
    }

    public void fail() {
        for (ShowSeat ss : selectedSeats) {
            ss.release();
        }
        this.status = BookingStatus.FAILED;
    }

    public void cancel() {
        for (ShowSeat ss : selectedSeats) {
            ss.release();
        }
        this.status = BookingStatus.CANCELLED;
    }
}