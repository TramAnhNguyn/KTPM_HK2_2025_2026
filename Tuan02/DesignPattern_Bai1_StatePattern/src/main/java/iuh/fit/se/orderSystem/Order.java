package iuh.fit.se.orderSystem;

public class Order {
    private OrderState state;
    private String orderId;

    public Order(String orderId) {
        this.orderId = orderId;
        // Mặc định khi khởi tạo, đơn hàng ở trạng thái "Mới tạo"
        this.state = new NewState();
    }

    // Setter để các State con có thể thay đổi trạng thái của Order
    public void setState(OrderState state) {
        this.state = state;
        System.out.println("\t[Trạng thái đơn hàng " + orderId + " đã đổi thành: " + state.getStatusName() + "]");
    }

    public OrderState getState() {
        return state;
    }

    // Hành động chính: Gọi hàm handle của trạng thái hiện tại
    public void process() {
        state.handle(this);
    }

    // Hành động hủy: Có thể gọi bất cứ lúc nào để gán trạng thái Hủy
    public void cancel() {
        System.out.println("!!! Yêu cầu hủy đơn hàng...");
        this.setState(new CancelledState());
        state.handle(this); // Gọi handle của CancelledState để hoàn tiền
    }
}
