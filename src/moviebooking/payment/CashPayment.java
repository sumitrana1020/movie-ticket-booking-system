package moviebooking.payment;

public class CashPayment extends Payment {

    public CashPayment(double amount) {
        super(amount);
    }

    @Override
    public boolean pay(double amount) {
        System.out.println("[Cash] Rs." + amount + " received");
        return true;
    }
}