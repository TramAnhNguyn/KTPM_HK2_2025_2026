FROM redis:latest

# Copy file cấu hình vào container
COPY redis.conf /usr/local/etc/redis/redis.conf

# Chạy redis-server với file cấu hình đã copy
CMD [ "redis-server", "/usr/local/etc/redis/redis.conf" ]