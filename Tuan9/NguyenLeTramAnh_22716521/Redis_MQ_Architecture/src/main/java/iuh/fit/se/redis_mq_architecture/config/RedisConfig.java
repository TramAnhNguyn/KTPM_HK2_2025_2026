package iuh.fit.se.redis_mq_architecture.config;

import iuh.fit.se.redis_mq_architecture.service.WriteService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
    public static final String WRITE_TOPIC = "product_write_topic";
    public static final String DELETE_TOPIC = "product_delete_topic"; // Kênh mới dành cho việc xóa

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return template;
    }

    // --- CẤU HÌNH CHO LUỒNG GHI (CREATE/UPDATE) ---
    @Bean
    public MessageListenerAdapter writeListener(WriteService writeService) {
        MessageListenerAdapter adapter = new MessageListenerAdapter(writeService, "processWriteRequest");
        adapter.setSerializer(new GenericJackson2JsonRedisSerializer());
        return adapter;
    }

    // --- CẤU HÌNH CHO LUỒNG XÓA (DELETE) ---
    @Bean
    public MessageListenerAdapter deleteListener(WriteService writeService) {
        // Chỉ định hàm "processDeleteRequest" trong WriteService sẽ xử lý
        MessageListenerAdapter adapter = new MessageListenerAdapter(writeService, "processDeleteRequest");
        adapter.setSerializer(new GenericJackson2JsonRedisSerializer());
        return adapter;
    }

    // --- GẮN NGƯỜI LẮNG NGHE VÀO KÊNH ---
    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
                                                   WriteService writeService) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        // Nghe kênh Ghi
        container.addMessageListener(writeListener(writeService), new ChannelTopic(WRITE_TOPIC));
        // Nghe kênh Xóa
        container.addMessageListener(deleteListener(writeService), new ChannelTopic(DELETE_TOPIC));
        return container;
    }
}
