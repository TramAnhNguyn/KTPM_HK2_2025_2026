import os

# Đọc biến môi trường APP_ENV
env = os.environ.get('APP_ENV', 'Không tìm thấy biến môi trường')
print(f"Môi trường hiện tại đang chạy là: {env}")