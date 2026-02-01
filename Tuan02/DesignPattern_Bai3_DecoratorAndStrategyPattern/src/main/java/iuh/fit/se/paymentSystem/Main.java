package iuh.fit.se.paymentSystem;

public class Main {
    static void main() {
        double originalAmount = 100.0;

        System.out.println("=== TRƯỜNG HỢP 1: CHỈ THANH TOÁN PAYPAL ===");
        Payment p1 = new PayPalPayment(); // Strategy đơn thuần
        p1.pay(originalAmount);


        System.out.println("\n=== TRƯỜNG HỢP 2: THẺ TÍN DỤNG + PHÍ XỬ LÝ ===");
        // Bọc Phí bên ngoài Thẻ
        Payment p2 = new ProcessingFeeDecorator(new CreditCardPayment());
        p2.pay(originalAmount);


        System.out.println("\n=== TRƯỜNG HỢP 3: COMBO ĐẦY ĐỦ (PAYPAL + PHÍ + GIẢM GIÁ) ===");
        // Bọc Giảm giá bên ngoài Phí, Phí bọc bên ngoài PayPal (Mô hình Củ hành tây)
        Payment p3 = new DiscountDecorator(
                new ProcessingFeeDecorator(
                        new PayPalPayment()
                )
        );
        p3.pay(originalAmount);
    }

}
