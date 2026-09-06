package moviebooking.model.entity;

import java.util.ArrayList;
import java.util.List;

public class Screen {
    private int screenNumber;
    private List<Seat> seats;

    public Screen(int screenNumber) {
        this.screenNumber = screenNumber;
        this.seats = new ArrayList<>();
    }

    public int getScreenNumber() {
        return screenNumber;
    }

    public void addSeat(Seat seat) {
        seats.add(seat);
    }

    public Seat findSeat(String seatNumber) {
        for (Seat seat : seats) {
            if (seat.getNumber().equals(seatNumber)) {
                return seat;
            }
        }
        return null;
    }

    public List<Seat> getSeats() {
        return seats;
    }
}