package moviebooking.model.booking;
import moviebooking.model.enums.SeatType;

import java.util.ArrayList;
import java.util.List;
import moviebooking.model.entity.Movie;
import moviebooking.model.entity.Screen;
import moviebooking.model.entity.Seat;

public class Show {
    private String startTime;
    private Movie movie;
    private Screen screen;
    private List<ShowSeat> showSeats;

    public Show(String startTime, Movie movie, Screen screen) {
        this.startTime = startTime;
        this.movie = movie;
        this.screen = screen;
        this.showSeats = new ArrayList<>();
        for (Seat seat : screen.getSeats()) {
            showSeats.add(new ShowSeat(seat));
        }
    }

    public String getStartTime() {
        return startTime;
    }

    public Movie getMovie() {
        return movie;
    }

    public Screen getScreen() {
        return screen;
    }

    public ShowSeat getShowSeat(String seatNumber) {
        for (ShowSeat ss : showSeats) {
            if (ss.getSeat().getNumber().equals(seatNumber)) {
                return ss;
            }
        }
        return null;
    }
    public void printSeatLayout() {
        System.out.println();
        System.out.println("  SCREEN-" + screen.getScreenNumber() + "  " + startTime + "  |  " + movie.getTitle());

        printSeatRow("SILVER", SeatType.SILVER);
        printSeatRow("GOLD", SeatType.GOLD);
        printSeatRow("PLATINUM", SeatType.PLATINUM);

        System.out.println();
        System.out.println("  ( [ ] = available   [X] = booked )");
    }

    private void printSeatRow(String label, SeatType type) {
        StringBuilder row = new StringBuilder("  " + label + "  ");
        for (ShowSeat ss : showSeats) {
            if (ss.getSeat().getType() == type) {
                String mark = ss.isAvailable() ? " " : "X";
                row.append(ss.getSeat().getNumber()).append("[").append(mark).append("] ");
            }
        }
        System.out.println(row.toString());
    }

    public List<ShowSeat> getShowSeats() {
        return showSeats;
    }

    public List<ShowSeat> getAvailableSeats() {
        List<ShowSeat> available = new ArrayList<>();
        for (ShowSeat ss : showSeats) {
            if (ss.isAvailable()) {
                available.add(ss);
            }
        }
        return available;
    }
}