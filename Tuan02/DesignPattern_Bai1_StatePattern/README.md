1. State Pattern hoạt động như thế nào trong bài này?
Về cơ bản, State Pattern giúp một đối tượng (Đơn hàng) thay đổi hành vi của nó khi trạng thái nội tại thay đổi. Nó biến mỗi trạng thái thành một class riêng biệt.

2. Lợi ích cụ thể của State Pattern trong bài toán này
Tại sao chúng ta lại tốn công tạo nhiều file như vậy? Dưới đây là 4 lợi ích cốt lõi:

💎 1. Loại bỏ cấu trúc điều kiện phức tạp (if-else / switch-case)
Đây là lợi ích lớn nhất. Thay vì một hàm process() dài 100 dòng với đủ loại if, bạn chia nhỏ nó thành 4 file class, mỗi file chỉ 10-15 dòng. Code trở nên cực kỳ trong sáng và dễ đọc.

💎 2. Tuân thủ nguyên tắc "Đóng/Mở" (Open/Closed Principle)
Giả sử sếp yêu cầu thêm trạng thái "Đổi trả" (Returned).

Nếu không dùng Pattern: Bạn phải mở file Order.java ra, tìm chỗ if-else, chèn thêm logic vào, rủi ro làm hỏng code cũ rất cao.

Nếu dùng Pattern: Bạn chỉ cần tạo class mới ReturnedState implement OrderState. Sau đó sửa nhẹ ở ShippedState để trỏ tới nó. Class Order hay NewState hoàn toàn không bị ảnh hưởng.