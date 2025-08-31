package mini.integration.customerservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableCaching
public class CacheConfig {

//    @Bean
//    public RedisCacheManager redisCacheManager(RedisConnectionFactory cf, ObjectMapper baseOm) {
//        // Shared base config (keys as strings, dates as ISO-8601)
//        ObjectMapper om = new ObjectMapper()
//                .registerModule(new JavaTimeModule())
//                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//
//
//        var keySer = new org.springframework.data.redis.serializer.StringRedisSerializer();
//        var valueSer = new org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer(om);
//
//        var base = RedisCacheConfiguration.defaultCacheConfig()
//                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(keySer))
//                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(valueSer))
//                // default value serializer (generic) for other caches if needed
//                .entryTtl(Duration.ofMinutes(5))
//                .disableCachingNullValues();
//
//        // Typed serializer JUST for CustomerProfileDTO
//        var customerDtoSer = new Jackson2JsonRedisSerializer<>(om,
//                mini.integration.customerservice.infrastructure.dto.CustomerProfileDTO.class);
//
//        Map<String, RedisCacheConfiguration> perCache = new HashMap<>();
//        perCache.put("customerProfiles",
//                base.entryTtl(Duration.ofMinutes(15))
//                        .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(customerDtoSer)));
//
//        return RedisCacheManager.builder(cf)
//                .cacheDefaults(base)
//                .withInitialCacheConfigurations(perCache)
//                .build();
//    }

}
