const axios = require('axios'); // Bạn cần chạy: npm install axios

const totalRequests = 100; // Giả lập 100 người bấm cùng lúc

console.log("🚀 Bắt đầu đợt Flash Sale...");

for (let i = 0; i < totalRequests; i++) {
    // Gửi request liên tục không chờ đợi
    axios.post('http://localhost:3001/buy')
        .then(res => {
            // Chỉ in ra log tượng trưng để đỡ rối màn hình
            // console.log(res.data.message); 
        })
        .catch(err => console.error("Lỗi mạng"));
}

console.log(`⚡ Đã bắn xong ${totalRequests} requests!`);