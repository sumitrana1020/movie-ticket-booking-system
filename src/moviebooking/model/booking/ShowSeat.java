package moviebooking.model.booking;

import moviebooking.model.entity.Seat;
import moviebooking.model.enums.SeatStatus;

public class ShowSeat {
    private Seat seat;
    private SeatStatus status;

    public ShowSeat(Seat seat) {
        this.seat = seat;
        this.status = SeatStatus.AVAILABLE;
    }

    public Seat getSeat() {
        return seat;
    }

    public boolean isAvailable() {
        return status == SeatStatus.AVAILABLE;
    }

    public void book() {
        this.status = SeatStatus.BOOKED;
    }

    public void release() {
        this.status = SeatStatus.AVAILABLE;
    }
}