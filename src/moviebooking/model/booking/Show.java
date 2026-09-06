package moviebooking.model.booking;

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