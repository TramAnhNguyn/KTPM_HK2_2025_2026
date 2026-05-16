package iuh.fit.se.redis_mq_architecture.repository;

import iuh.fit.se.redis_mq_architecture.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
