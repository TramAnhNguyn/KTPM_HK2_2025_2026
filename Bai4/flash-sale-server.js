const express = require('express');
const amqp = require('amqplib');
const app = express();
const port = 3001;

let channel;

// Kết nối RabbitMQ
async function connectRabbitMQ() {
    try {
        const connection = await amqp.connect('amqp://localhost:5672');
        channel = await connection.createChannel();
        await channel.assertQueue('orders_queue');
        console.log("✅ Server đã sẵn sàng nhận đơn Flash Sale!");
    } catch (ex) {
        console.error("Lỗi kết nối RabbitMQ:", ex);
    }
}
connectRabbitMQ();

// API Mua hàng (Chịu tải cao)
app.post('/buy', async (req, res) => {
    const user = 'User-' + Math.floor(Math.random() * 1000);

    // Thay vì chọc vào Database (rất chậm và dễ sập), ta chỉ đẩy vào Queue
    // Việc này chỉ mất vài miliseconds
    if (!channel) return res.status(500).send("Hệ thống lỗi");

    const orderData = JSON.stringify({ user: user, timestamp: Date.now() });

    // Đẩy vào hàng đợi
    channel.sendToQueue('orders_queue', Buffer.from(orderData));

    // Trả lời ngay lập tức để giải phóng kết nối cho người khác vào
    res.json({
        message: "Đã tiếp nhận yêu cầu! Vui lòng chờ kết quả...",
        user: user
    });
});

app.listen(port, () => {
    console.log(`Server Flash Sale chạy tại http://localhost:${port}`);
});