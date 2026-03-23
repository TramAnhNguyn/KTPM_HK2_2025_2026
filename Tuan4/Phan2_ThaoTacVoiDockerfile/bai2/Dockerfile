# Sử dụng python:3.9 làm base image
FROM python:3.9

# Thiết lập thư mục làm việc trong container
WORKDIR /app

# Copy các file cần thiết vào container
COPY requirements.txt requirements.txt
COPY app.py app.py

# Cài đặt thư viện
RUN pip install -r requirements.txt

# Mở cổng 5000
EXPOSE 5000

# Lệnh chạy ứng dụng
CMD ["python", "app.py"]