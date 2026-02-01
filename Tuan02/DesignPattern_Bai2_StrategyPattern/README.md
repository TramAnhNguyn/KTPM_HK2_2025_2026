# 🛒 PROJECT: HỆ THỐNG TÍNH TOÁN THUẾ SẢN PHẨM (TAX CALCULATION SYSTEM)

> **Mô tả:** Hệ thống quản lý giá sản phẩm và tính toán các loại thuế khác nhau (VAT, Thuế tiêu thụ, Thuế xa xỉ) một cách linh hoạt mà không cần sửa đổi mã nguồn cốt lõi.

---

1. 💡 PHÂN TÍCH DESIGN PATTERN: STRATEGY
Tại sao chọn Strategy Pattern?
Thay vì sử dụng hàng loạt câu lệnh if-else hoặc switch-case bên trong class Product để kiểm tra loại thuế, chúng ta tách mỗi công thức tính thuế ra thành một class riêng biệt.

Lợi ích cốt lõi:
Loại bỏ mã nguồn phức tạp: Không còn if (type == VAT) ... else if (type == LUXURY) ....

Nguyên lý Open/Closed (O trong SOLID): Dễ dàng thêm loại thuế mới (ví dụ: Thuế Môi trường) bằng cách tạo file mới mà không cần sửa code cũ của class Product.

Linh hoạt (Runtime change): Có thể thay đổi công thức tính thuế cho một sản phẩm ngay khi chương trình đang chạy.

2. 📂 CẤU TRÚC THƯ MỤC (PROJECT STRUCTURE)

src/
└── tax_system/
    ├── TaxStrategy.java          # (Interface) Hợp đồng chung cho mọi loại thuế
    ├── VATStrategy.java          # (Implementation) Thuế VAT 10%
    ├── LuxuryTaxStrategy.java    # (Implementation) Thuế xa xỉ 50%
    ├── ConsumptionTaxStrategy.java # (Implementation) Thuế tiêu thụ 5%
    ├── Product.java              # (Context) Đối tượng sản phẩm
    └── Main.java                 # (Client) Chương trình chạy thử


3. ✅ KẾT LUẬN
Dự án này đã minh họa thành công việc áp dụng Strategy Pattern trong Java. Hệ thống đạt được tính mô-đun hóa cao, dễ dàng bảo trì và mở rộng các loại thuế mới trong tương lai.