package iuh.fit.se.paymentSystem;

public class CreditCardPayment implements Payment{
    @Override
    public void pay(double amount) {
        System.out.println("✅ [CreditCard] Đã thanh toán $" + amount + " qua Thẻ tín dụng.");
    }
}
