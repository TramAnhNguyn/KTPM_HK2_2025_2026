package iuh.fit.se.orderSystem;

public class ProcessingState implements OrderState {
    @Override
    public void handle(Order context) {
        System.out.println("LOG: Đang đóng gói và liên hệ đơn vị vận chuyển...");
        // Logic in phiếu gửi hàng...

        // Chuyển trạng thái sang "Đã giao"
        context.setState(new ShippedState());
    }

    @Override
    public String getStatusName() {
        return "Đang xử lý";
    }
}
