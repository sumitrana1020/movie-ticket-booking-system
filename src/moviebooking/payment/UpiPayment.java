package moviebooking.payment;

public class UpiPayment extends Payment {
    private String upiId;

    public UpiPayment(double amount, String upiId) {
        super(amount);
        this.upiId = upiId;
    }

    @Override
    public boolean pay(double amount) {
        System.out.println("[UPI] Rs." + amount + " paid successfully via " + upiId);
        return true;
    }
}