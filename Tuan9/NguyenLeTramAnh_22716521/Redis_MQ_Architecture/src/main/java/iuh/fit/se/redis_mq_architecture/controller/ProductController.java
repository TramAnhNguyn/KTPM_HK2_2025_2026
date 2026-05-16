package iuh.fit.se.redis_mq_architecture.controller;


import iuh.fit.se.redis_mq_architecture.config.RedisConfig;
import iuh.fit.se.redis_mq_architecture.entity.Product;
import iuh.fit.se.redis_mq_architecture.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    // --- LUỒNG GHI (POST/PUT) - Dùng Redis Pub/Sub ---
    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody Product product) {
        // Xuất bản (Publish) message lên Redis Topic rồi trả về ngay
        redisTemplate.convertAndSend(RedisConfig.WRITE_TOPIC, product);
        return ResponseEntity.accepted().body("Request đã được gửi qua Redis MQ và đang xử lý bất đồng bộ.");
    }

    // --- LUỒNG ĐỌC (GET) - Dùng Redis Cache ---
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        String redisKey = "product:" + id;

        Product cachedProduct = (Product) redisTemplate.opsForValue().get(redisKey);
        if (cachedProduct != null) {
            System.out.println("-> CACHE HIT: Lấy dữ liệu siêu nhanh từ Redis.");
            return ResponseEntity.ok(cachedProduct);
        }

        System.out.println("-> CACHE MISS: Xuống MariaDB tìm dữ liệu.");
        Product dbProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm"));

        redisTemplate.opsForValue().set(redisKey, dbProduct);

        return ResponseEntity.ok(dbProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        // Gắn ID vào object để DB hiểu đây là Update chứ không phải Create
        product.setId(id);

        // Quăng vào Topic Ghi giống hệt lúc Create
        redisTemplate.convertAndSend(RedisConfig.WRITE_TOPIC, product);

        return ResponseEntity.accepted().body("Request UPDATE đã được gửi qua Redis MQ.");
    }

    // --- LUỒNG XÓA (DELETE) - Dùng kênh Xóa ---
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        // Tạo một object rỗng, chỉ nhét mỗi cái ID vào để gửi đi cho an toàn
        Product productToDelete = new Product();
        productToDelete.setId(id);

        // Quăng vào Topic Xóa
        redisTemplate.convertAndSend(RedisConfig.DELETE_TOPIC, productToDelete);

        return ResponseEntity.accepted().body("Request DELETE đã được gửi qua Redis MQ.");
    }
}
