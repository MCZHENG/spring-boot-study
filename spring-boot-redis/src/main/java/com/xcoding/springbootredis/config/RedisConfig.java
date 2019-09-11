package com.xcoding.springbootredis.config;


import com.xcoding.springbootredis.entity.User;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * 对RedisTemplate进行序列化
 */
@Configuration
@AutoConfigureAfter(RedisAutoConfiguration.class)
public class RedisConfig {

    @Bean("userRedisTemplate")
    public RedisTemplate<String, User> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String ,User> template = new RedisTemplate<String ,User>();

        Jackson2JsonRedisSerializer<User> j = new Jackson2JsonRedisSerializer<User>(User.class);
        // value 值的序列化
        template.setValueSerializer(j);
        template.setHashValueSerializer(j);
        // key 值的序列化
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());

        template.setConnectionFactory(redisConnectionFactory);

        return template;

    }

}

