package iuh.fit.se.paymentSystem;

public class PayPalPayment implements Payment{
    @Override
    public void pay(double amount) {
        System.out.println("✅ [PayPal] Đã thanh toán $" + amount + " qua PayPal.");
    }
}
