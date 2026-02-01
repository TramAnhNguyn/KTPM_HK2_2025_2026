package iuh.fit.se.taxSystem;

import java.text.DecimalFormat;

public class Product {
    private String name;
    private double basePrice;
    private TaxStrategy taxStrategy; // Điểm mấu chốt: Biến này giữ thuật toán tính thuế

    // Constructor: Khi tạo sản phẩm, ta "tiêm" (inject) loại thuế vào
    public Product(String name, double basePrice, TaxStrategy taxStrategy) {
        this.name = name;
        this.basePrice = basePrice;
        this.taxStrategy = taxStrategy;
    }

    // Hàm tính tổng tiền cuối cùng
    public double getFinalPrice() {
        double tax = taxStrategy.calculateTax(basePrice); // Gọi thuật toán đã được tiêm vào
        return basePrice + tax;
    }

    // Hàm in thông tin chi tiết
    public void printReceipt() {
        DecimalFormat df = new DecimalFormat("#,###.00");
        double tax = taxStrategy.calculateTax(basePrice);
        double total = getFinalPrice();

        System.out.println("----- HÓA ĐƠN: " + name + " -----");
        System.out.println("Giá gốc:      $" + df.format(basePrice));
        System.out.println("Loại thuế áp dụng: " + taxStrategy.getClass().getSimpleName());
        System.out.println("Tiền thuế:    $" + df.format(tax));
        System.out.println("TỔNG CỘNG:    $" + df.format(total));
        System.out.println("--------------------------------");
    }

    // Setter: Cho phép đổi loại thuế lúc đang chạy (nếu cần)
    public void setTaxStrategy(TaxStrategy taxStrategy) {
        this.taxStrategy = taxStrategy;
    }
}
