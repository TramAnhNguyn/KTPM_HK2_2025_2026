# 💳 PROJECT: HỆ THỐNG THANH TOÁN (PAYMENT SYSTEM)

> **Mô tả:** Hệ thống xử lý thanh toán linh hoạt, cho phép lựa chọn phương thức thanh toán (Thẻ, PayPal) và áp dụng động các tính năng bổ sung (Phí xử lý, Mã giảm giá) theo cấu trúc xếp chồng (Stackable).

---

## 1. 🏗 KIẾN TRÚC HỆ THỐNG (ARCHITECTURE)

Hệ thống được thiết kế theo mô hình **C4 Model**, minh họa luồng đi của dữ liệu qua các lớp vỏ bọc (Decorators) trước khi đến lõi xử lý (Strategy).

-----------------------------------------------

2. 💡 PHÂN TÍCH DESIGN PATTERN
Bài toán sử dụng sự kết hợp mạnh mẽ của 2 Pattern:

2.1. Strategy Pattern (Cho phương thức thanh toán)
Vị trí: CreditCardPayment, PayPalPayment.

Mục đích: Giúp người dùng LỰA CHỌN (Select) một phương thức thanh toán duy nhất tại thời điểm chạy.

Tính chất: Thay thế lẫn nhau (Mutually Exclusive).

2.2. Decorator Pattern (Cho tính năng bổ sung)
Vị trí: ProcessingFeeDecorator, DiscountDecorator.

Mục đích: Giúp người dùng MỞ RỘNG (Extend) tính năng cho phương thức thanh toán mà không cần sửa code gốc.

Tính chất: Xếp chồng (Stackable). Có thể áp dụng nhiều lớp decorator cùng lúc (vừa có phí, vừa có giảm giá).

----------------------------------------------------


3. 📂 CẤU TRÚC THƯ MỤC (PROJECT STRUCTURE)

src/
└── payment_system/
    ├── Payment.java                # (Interface) Hợp đồng chung
    ├── CreditCardPayment.java      # (Strategy) Thanh toán thẻ
    ├── PayPalPayment.java          # (Strategy) Thanh toán PayPal
    ├── PaymentDecorator.java       # (Abstract Decorator) Lớp vỏ trừu tượng
    ├── ProcessingFeeDecorator.java # (Concrete Decorator) Thêm phí
    ├── DiscountDecorator.java      # (Concrete Decorator) Giảm giá
    └── Main.java                   # (Client) Chương trình chạy thử

4. 🏆 Kết luận về bài toán 3
Sự kết hợp này mang lại sức mạnh cực lớn:

Strategy (PayPal/CreditCard): Giúp bạn THAY THẾ phương thức thanh toán. (Chọn 1 trong N).

Decorator (Fee/Discount): Giúp bạn MỞ RỘNG tính năng cho phương thức đó mà không cần sửa code class gốc. Bạn có thể lồng bao nhiêu lớp Decorator tùy thích (ví dụ: áp dụng 2 mã giảm giá liên tiếp).