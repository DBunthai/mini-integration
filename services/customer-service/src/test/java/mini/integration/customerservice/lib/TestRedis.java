package mini.integration.customerservice.lib;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.time.Duration;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TestRedis {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    public void testRedisConnection() {
        String key = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(key, "pong", Duration.ofMinutes(1));
        String result = redisTemplate.opsForValue().get(key);
        assertEquals("pong", result);
    }
}
