package iuh.fit.se.paymentSystem;

public class ProcessingFeeDecorator extends PaymentDecorator{
    public ProcessingFeeDecorator(Payment payment) {
        super(payment);
    }

    @Override
    public void pay(double amount) {
        double fee = 5.0;
        double total = amount + fee;
        System.out.println("   -> [Phí] Cộng thêm phí xử lý: $" + fee);

        // Gọi lớp bên trong với số tiền MỚI (đã cộng phí)
        super.pay(total);
    }
}
