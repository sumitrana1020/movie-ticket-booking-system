package moviebooking.service;

import java.util.List;
import moviebooking.model.booking.ShowSeat;

public class PriceCalculator {

    public double calculate(List<ShowSeat> seats) {
        double total = 0;
        for (ShowSeat ss : seats) {
            total += ss.getSeat().getPrice();
        }
        return total;
    }
}