package iuh.fit.se.orderSystem;

public class Main {
    static void main() {
        // KỊCH BẢN 1: Đơn hàng thành công
        System.out.println("=== KỊCH BẢN 1: GIAO HÀNG THÀNH CÔNG ===");
        Order order1 = new Order("#DH001");

        order1.process(); // Đang ở New -> Kiểm tra -> Chuyển sang Processing
        order1.process(); // Đang ở Processing -> Đóng gói -> Chuyển sang Shipped
        order1.process(); // Đang ở Shipped -> Hoàn tất.

        System.out.println("\n=== KỊCH BẢN 2: HỦY ĐƠN GIỮA CHỪNG ===");
        Order order2 = new Order("#DH002");

        order2.process(); // Mới tạo -> Đang xử lý
        // Đột nhiên khách muốn hủy khi đang xử lý
        order2.cancel();  // Chuyển ngay sang Cancelled và hoàn tiền
    }
}
