const express = require('express');
const app = express();
const port = 3000;

// Giả lập hàm lưu đơn hàng vào Database (Nhanh - 50ms)
const createOrderInDB = async (orderId) => {
    return new Promise(resolve => {
        setTimeout(() => {
            console.log(`[Database] Đã lưu đơn hàng ${orderId} thành công!`);
            resolve(true);
        }, 50);
    });
};

// Giả lập hàm gửi Email (Chậm - 5 giây)
const sendEmailConfirm = async (email) => {
    return new Promise(resolve => {
        console.log(`[Email Service] Đang gửi email tới ${email}... (Vui lòng đợi)`);
        setTimeout(() => {
            console.log(`[Email Service] Đã gửi email xong!`);
            resolve(true);
        }, 5000); // Giả vờ mạng lag hoặc server mail chậm mất 5s
    });
};

// API Đặt vé / Đặt hàng
app.post('/dat-ve', async (req, res) => {
    const startTime = Date.now();
    const orderId = 'DH-' + Math.floor(Math.random() * 1000);

    // Bước 1: Tạo đơn hàng (Nhanh)
    await createOrderInDB(orderId);

    // Bước 2: Gửi email (Chậm)
    // TẠI ĐÂY: Nếu KHÔNG dùng MQ, code sẽ bị kẹt lại ở dòng này 5 giây
    // User phải nhìn vòng quay loading trên trình duyệt
    await sendEmailConfirm('user@example.com');

    const endTime = Date.now();
    const totalTime = (endTime - startTime) / 1000;

    // Bước 3: Trả kết quả
    res.send({
        status: 'success',
        message: `Đặt vé thành công! Mã đơn: ${orderId}`,
        time_taken: `${totalTime} giây`
    });
});

app.listen(port, () => {
    console.log(`Server đang chạy tại http://localhost:${port}`);
});