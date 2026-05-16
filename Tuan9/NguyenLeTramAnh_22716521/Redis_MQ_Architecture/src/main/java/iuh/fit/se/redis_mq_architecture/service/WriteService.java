package iuh.fit.se.redis_mq_architecture.service;

import iuh.fit.se.redis_mq_architecture.entity.Product;
import iuh.fit.se.redis_mq_architecture.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
@Service
public class WriteService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    // Không cần @RabbitListener nữa, RedisConfig sẽ tự động ánh xạ vào hàm này
    public void processWriteRequest(Product product) {
        System.out.println("==> BẾP NHẬN ORDER (TỪ REDIS PUB/SUB): Tạo Product " + product.getName());

        // 1. Lưu vào MariaDB
        Product savedProduct = productRepository.save(product);

        // 2. Cập nhật vào Redis Cache
        String redisKey = "product:" + savedProduct.getId();
        redisTemplate.opsForValue().set(redisKey, savedProduct);

        System.out.println("==> BẾP ĐÃ NẤU XONG: Đã lưu DB và đưa lên tủ kính Redis!");
    }

    public void processDeleteRequest(Product product) {
        Long productId = product.getId();
        System.out.println("==> BẾP NHẬN LỆNH HỦY (TỪ REDIS PUB/SUB): Xóa Product ID " + productId);

        try {
            // 1. Xóa dưới bếp (MariaDB)
            productRepository.deleteById(productId);

            // 2. Dọn tủ kính (Redis Cache)
            String redisKey = "product:" + productId;
            redisTemplate.delete(redisKey);

            System.out.println("==> HỦY THÀNH CÔNG: Đã xóa khỏi DB và dọn sạch Cache!");
        } catch (Exception e) {
            System.out.println("==> LỖI KHI XÓA: Sản phẩm không tồn tại hoặc lỗi DB.");
        }
    }


}
