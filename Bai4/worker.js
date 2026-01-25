const amqp = require('amqplib');

// Giả lập kho hàng trong Database chỉ có 10 cái
let inventory = 10;

async function startWorker() {
    try {
        const connection = await amqp.connect('amqp://localhost:5672');
        const channel = await connection.createChannel();
        await channel.assertQueue('orders_queue');

        // QUAN TRỌNG: Dòng này bảo RabbitMQ "Chỉ đưa cho tôi 1 việc tại 1 thời điểm thôi"
        // Đây chính là cơ chế chống sập (Throttle)
        channel.prefetch(1);

        console.log("👷 Worker đang chạy... Kho hàng hiện tại: " + inventory);

        channel.consume('orders_queue', async (data) => {
            if (data) {
                const order = JSON.parse(data.content.toString());

                // Giả lập xử lý Database mất 1 chút thời gian (100ms)
                await new Promise(r => setTimeout(r, 100));

                if (inventory > 0) {
                    inventory--;
                    console.log(`✅ Đơn thành công cho ${order.user}. Kho còn: ${inventory}`);
                } else {
                    console.log(`❌ HẾT HÀNG! Xin lỗi ${order.user}.`);
                    // Logic thực tế: Gửi thông báo/email báo hết hàng cho user này
                }

                // Báo xong việc để nhận đơn tiếp theo
                channel.ack(data);
            }
        });
    } catch (ex) {
        console.error(ex);
    }
}

startWorker();