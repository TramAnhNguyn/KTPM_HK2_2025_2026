package iuh.fit.se.orderSystem;

public class NewState implements OrderState {
    @Override
    public void handle(Order context) {
        System.out.println("LOG: Đang kiểm tra thông tin đơn hàng...");
        // Logic kiểm tra kho, tài khoản... (giả lập)

        System.out.println("-> Kiểm tra hợp lệ.");

        // Chuyển trạng thái sang "Đang xử lý"
        context.setState(new ProcessingState());
    }

    @Override
    public String getStatusName() {
        return "Mới tạo";
    }
}
