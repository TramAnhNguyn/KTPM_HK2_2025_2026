# Stage 1: Build code
FROM node:18 AS builder
WORKDIR /app
COPY package.json ./
RUN npm install
COPY app.js ./

# Stage 2: Chạy ứng dụng
FROM node:18-alpine
WORKDIR /app
# Chỉ copy những file cần thiết từ stage 1 sang stage 2 để làm nhẹ image
COPY --from=builder /app/node_modules ./node_modules
COPY --from=builder /app/app.js ./app.js

EXPOSE 3000
CMD ["node", "app.js"]