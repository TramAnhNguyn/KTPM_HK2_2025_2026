package iuh.fit.se.paymentSystem;

public abstract class PaymentDecorator implements Payment {
    // Giữ một đối tượng Payment khác bên trong (Lõi hoặc một lớp vỏ khác)
    protected Payment wrappedPayment;

    public PaymentDecorator(Payment payment) {
        this.wrappedPayment = payment;
    }

    @Override
    public void pay(double amount) {
        // Mặc định: Chuyển tiếp yêu cầu cho lớp bên trong
        wrappedPayment.pay(amount);
    }
}
