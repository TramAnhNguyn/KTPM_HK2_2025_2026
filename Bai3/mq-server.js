const express = require('express');
const amqp = require('amqplib'); // Thư viện kết nối RabbitMQ
const app = express();
const port = 3001; // Chạy port khác để dễ so sánh

// 1. Giả lập lưu DB (Nhanh - 50ms)
const createOrderInDB = async (orderId) => {
    return new Promise(resolve => setTimeout(() => resolve(true), 50));
};

// 2. Kết nối RabbitMQ
let channel, connection;
async function connectRabbitMQ() {
    try {
        const amqpServer = 'amqp://localhost:5672';
        connection = await amqp.connect(amqpServer);
        channel = await connection.createChannel();
        await channel.assertQueue('email_queue'); // Tạo hàng đợi tên là 'email_queue'
        console.log("✅ Đã kết nối tới RabbitMQ");
    } catch (ex) {
        console.error(ex);
    }
}
connectRabbitMQ();

// API Đặt vé (Phiên bản dùng MQ)
app.post('/dat-ve-mq', async (req, res) => {
    const startTime = Date.now();
    const orderId = 'DH-' + Math.floor(Math.random() * 1000);
    const email = 'user@example.com';

    // B1: Lưu đơn hàng (Nhanh)
    await createOrderInDB(orderId);

    // B2: Đẩy việc gửi email vào Queue (Cực nhanh - < 10ms)
    // Thay vì ngồi chờ gửi email, ta chỉ gửi tín hiệu vào RabbitMQ
    const message = JSON.stringify({ orderId, email });
    channel.sendToQueue('email_queue', Buffer.from(message));
    console.log(`[Server] Đã đẩy task gửi email cho đơn ${orderId} vào Queue`);

    const endTime = Date.now();
    const totalTime = (endTime - startTime) / 1000;

    // B3: Trả kết quả NGAY LẬP TỨC cho user
    res.send({
        status: 'success',
        message: `Đặt vé thành công! Mã đơn: ${orderId}`,
        note: 'Email xác nhận sẽ được gửi sau ít phút.',
        time_taken: `${totalTime} giây` // Bạn sẽ thấy số này cực nhỏ (~0.05s)
    });
});

app.listen(port, () => {
    console.log(`Server MQ chạy tại http://localhost:${port}`);
});