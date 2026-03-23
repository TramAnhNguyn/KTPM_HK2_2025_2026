FROM postgres:15

# Docker image của postgres sẽ tự động chạy các script .sql được copy vào thư mục này khi khởi tạo lần đầu
COPY init.sql /docker-entrypoint-initdb.d/