package iuh.fit.se.paymentSystem;

public class DiscountDecorator extends PaymentDecorator{
    public DiscountDecorator(Payment payment) {
        super(payment);
    }

    @Override
    public void pay(double amount) {
        double discount = 10.0;
        double total = amount - discount;
        System.out.println("   -> [Voucher] Áp dụng mã giảm giá: -$" + discount);

        // Gọi lớp bên trong với số tiền MỚI (đã trừ voucher)
        super.pay(total);
    }
}
