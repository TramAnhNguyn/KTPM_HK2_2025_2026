package iuh.fit.se.orderSystem;

public interface OrderState {
    // Phương thức xử lý chính, nhận vào đối tượng Order để có thể chuyển trạng thái
    void handle(Order context);

    // Lấy tên trạng thái để hiển thị
    String getStatusName();
}
