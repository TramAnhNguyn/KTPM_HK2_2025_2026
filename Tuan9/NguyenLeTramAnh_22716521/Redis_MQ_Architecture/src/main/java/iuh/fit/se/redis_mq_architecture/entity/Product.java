package iuh.fit.se.redis_mq_architecture.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double price;
}
