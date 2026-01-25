const amqp = require('amqplib');

// Giả lập hàm gửi Email (Chậm - 5 giây)
const sendEmailConfirm = async (email) => {
    return new Promise(resolve => {
        console.log(`   📧 [Worker] Đang gửi email tới ${email}...`);
        setTimeout(() => {
            console.log(`   ✅ [Worker] Đã gửi email xong!`);
            resolve(true);
        }, 5000);
    });
};

async function startWorker() {
    try {
        const amqpServer = 'amqp://localhost:5672';
        const connection = await amqp.connect(amqpServer);
        const channel = await connection.createChannel();
        await channel.assertQueue('email_queue');

        console.log("👷 Worker đang chạy và chờ việc trong hàng đợi...");

        // Lắng nghe hàng đợi
        channel.consume('email_queue', async (data) => {
            if (data) {
                const task = JSON.parse(data.content.toString());
                console.log(`\n📥 [Worker] Nhận được yêu cầu gửi email cho đơn: ${task.orderId}`);

                // Thực hiện việc nặng (Gửi email mất 5s)
                await sendEmailConfirm(task.email);

                // Báo cho RabbitMQ biết là đã làm xong, có thể xóa message đi
                channel.ack(data);
            }
        });
    } catch (ex) {
        console.error(ex);
    }
}

startWorker();