package iuh.fit.se.taxSystem;

public class Main {
    static void main() {
        // 1. Tạo một chiếc iPhone chịu thuế VAT 10%
        Product p1 = new Product("iPhone 15", 1000.0, new VATStrategy());
        p1.printReceipt();

        // 2. Tạo một chiếc Xe hơi siêu sang chịu thuế Xa xỉ 50%
        Product p2 = new Product("Lamborghini", 200000.0, new LuxuryTaxStrategy());
        p2.printReceipt();

        // 3. Tạo một gói Mì tôm chịu thuế Tiêu thụ 5%
        Product p3 = new Product("Hao Hao Noodles", 10.0, new ConsumptionTaxStrategy());
        p3.printReceipt();

        // 4. Test tính năng thay đổi chiến lược động (Dynamic)
        // Giả sử iPhone bỗng nhiên bị đánh thuế xa xỉ
        System.out.println("\n>>> CẬP NHẬT: iPhone bị đánh thuế xa xỉ!");
        p1.setTaxStrategy(new LuxuryTaxStrategy());
        p1.printReceipt();
    }
}
