package moviebooking.model.entity;

import moviebooking.model.enums.SeatType;

public class Seat {
    public static final double SILVER_PRICE = 150;
    public static final double GOLD_PRICE = 250;
    public static final double PLATINUM_PRICE = 400;

    private String number;
    private SeatType type;

    public Seat(String number, SeatType type) {
        this.number = number;
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public SeatType getType() {
        return type;
    }

    public double getPrice() {
        if (type == SeatType.SILVER) return SILVER_PRICE;
        if (type == SeatType.GOLD) return GOLD_PRICE;
        return PLATINUM_PRICE;
    }
}