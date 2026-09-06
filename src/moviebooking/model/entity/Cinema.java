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

    public void printMovies() {
        List<Movie> movies = new ArrayList<>();
        for (Show show : shows) {
            if (!movies.contains(show.getMovie())) {
                movies.add(show.getMovie());
            }
        }
        for (int i = 0; i < movies.size(); i++) {
            Movie m = movies.get(i);
            System.out.println("  [" + (i + 1) + "] " + m.getTitle() + "\t" + m.getLanguage() + "\t" + m.getDuration() + " min");
        }
    }

    public Movie getMovieByIndex(int index) {
        List<Movie> movies = new ArrayList<>();
        for (Show show : shows) {
            if (!movies.contains(show.getMovie())) {
                movies.add(show.getMovie());
            }
        }
        if (index < 1 || index > movies.size()) return null;
        return movies.get(index - 1);
    }

    public List<Show> getShowsForMovie(Movie movie) {
        List<Show> result = new ArrayList<>();
        for (Show show : shows) {
            if (show.getMovie() == movie) {
                result.add(show);
            }
        }
        return result;
    }

    public void printShowsForMovie(Movie movie) {
        List<Show> movieShows = getShowsForMovie(movie);
        for (int i = 0; i < movieShows.size(); i++) {
            Show s = movieShows.get(i);
            System.out.println("  [" + (i + 1) + "] Screen-" + s.getScreen().getScreenNumber() + "\t" + s.getStartTime());
        }
    }
    public Show getShowByIndex(Movie movie, int index) {
        List<Show> movieShows = getShowsForMovie(movie);
        if (index < 1 || index > movieShows.size()) return null;
        return movieShows.get(index - 1);
    }
}