package moviebooking.payment;

public class CardPayment extends Payment {
    private String cardNumber;

    public CardPayment(double amount, String cardNumber) {
        super(amount);
        this.cardNumber = cardNumber;
    }

    @Override
    public boolean pay(double amount) {
        System.out.println("[Card] Rs." + amount + " paid successfully");
        return true;
    }
}