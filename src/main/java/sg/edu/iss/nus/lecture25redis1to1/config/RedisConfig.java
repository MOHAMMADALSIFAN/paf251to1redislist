package sg.edu.iss.nus.lecture25redis1to1.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
    
    @Value("${spring.data.redis.host}")
    private String host;
    
    @Value("${spring.data.redis.port}")
    private int port;
    
    @Value("${spring.data.redis.username}")
    private String username;
    
    @Value("${spring.data.redis.password}")
    private String password;

    @Bean
    public RedisConnectionFactory createConnectionFactory() {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration(host, port);
        
        config.setDatabase(0);
        
        if (!username.isEmpty() || !password.isEmpty()) {
            config.setUsername(username);
            config.setPassword(password);
        }

        JedisClientConfiguration clientConfig = JedisClientConfiguration.builder().build();
        
        JedisConnectionFactory factory = new JedisConnectionFactory(config, clientConfig);
        factory.afterPropertiesSet();
        
        return factory;
    }

    @Bean("orderRedisTemplate")  // Changed from "order" to be more specific
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, String> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        
        // Configure serializers
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new StringRedisSerializer());
        
        return template;
    }
}