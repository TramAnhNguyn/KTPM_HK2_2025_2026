package iuh.fit.se.orderSystem;

public class ShippedState implements OrderState {
    @Override
    public void handle(Order context) {
        // Đã giao rồi thì chỉ cập nhật hệ thống, không chuyển trạng thái nữa
        System.out.println("LOG: Cập nhật hệ thống: Khách đã nhận hàng thành công.");
    }

    @Override
    public String getStatusName() {
        return "Đã giao";
    }
}
