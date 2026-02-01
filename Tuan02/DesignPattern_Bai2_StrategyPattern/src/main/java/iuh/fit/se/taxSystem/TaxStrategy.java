package iuh.fit.se.taxSystem;

public interface TaxStrategy {
    double calculateTax(double price);
    // Input: Giá gốc sản phẩm
    // Output: Tiền thuế phải đóng
}
