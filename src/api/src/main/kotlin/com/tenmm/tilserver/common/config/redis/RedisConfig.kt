package com.tenmm.tilserver.common.config.redis

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.data.redis.serializer.RedisSerializationContext
import org.springframework.data.redis.core.convert.RedisCustomConversions
import java.util.Arrays

@Configuration
class RedisConfig {

    @Primary
    @Bean
    fun connectionFactory(): ReactiveRedisConnectionFactory {
        return LettuceConnectionFactory("127.0.0.1", 6379)
    }

    @Bean
    fun securityTokenRedisTemplate(connectionFactory: ReactiveRedisConnectionFactory): ReactiveRedisTemplate<String, String> {
        return ReactiveRedisTemplate(connectionFactory, RedisSerializationContext.string())
    }

    @Bean
    fun redisCustomConversions(byteToTimestamp: BytesToTimestampConverter): RedisCustomConversions {
        return RedisCustomConversions(Arrays.asList(byteToTimestamp))
    }
}
