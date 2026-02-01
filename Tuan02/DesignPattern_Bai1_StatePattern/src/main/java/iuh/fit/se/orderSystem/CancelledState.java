package iuh.fit.se.orderSystem;

public class CancelledState implements OrderState{
    @Override
    public void handle(Order context) {
        System.out.println("LOG: Đang tiến hành thủ tục hoàn tiền cho khách...");
        System.out.println("-> Đã hoàn tiền xong.");
    }

    @Override
    public String getStatusName() {
        return "Đã hủy";
    }
}
