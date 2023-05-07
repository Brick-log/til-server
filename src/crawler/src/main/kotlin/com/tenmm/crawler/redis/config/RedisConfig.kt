package com.tenmm.crawler.config.redis

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.core.convert.RedisCustomConversions
import java.util.Arrays

@Configuration
class RedisConfig {

    @Bean
    fun redisCustomConversions(timestampToBytes: TimestampToBytesConverter): RedisCustomConversions {
        return RedisCustomConversions(Arrays.asList(timestampToBytes))
    }
}
