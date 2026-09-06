package moviebooking.model.entity;

import java.util.ArrayList;
import java.util.List;
import moviebooking.model.booking.Show;

public class Cinema {
    private String name;
    private List<Screen> screens;
    private List<Show> shows;

    public Cinema(String name) {
        this.name = name;
        this.screens = new ArrayList<>();
        this.shows = new ArrayList<>();
    }

    public void addScreen(Screen screen) {
        screens.add(screen);
    }

    public List<Screen> getScreens() {
        return screens;
    }

    public void addShow(Show show) {
        shows.add(show);
    }

    public List<Show> getShows() {
        return shows;
    }
}