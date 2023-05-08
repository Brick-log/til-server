package com.tenmm.tilserver.config

import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.tenmm.tilserver.outbound.redis.entity.ParsedPostEntity
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext

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
    fun draftRedisTemplate(connectionFactory: ReactiveRedisConnectionFactory): ReactiveRedisTemplate<String, String> {
        return ReactiveRedisTemplate(connectionFactory, RedisSerializationContext.string())
    }

    @Bean
    fun parsedPostRedisTemplate(connectionFactory: ReactiveRedisConnectionFactory): ReactiveRedisTemplate<String, ParsedPostEntity> {
        val objMapper = jacksonObjectMapper()
        objMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        objMapper.registerModules(JavaTimeModule(), Jdk8Module())

        val serializer = RedisSerializationContext.newSerializationContext<String, ParsedPostEntity>(
            Jackson2JsonRedisSerializer(objMapper, ParsedPostEntity::class.java)
        )
            .build()
        return ReactiveRedisTemplate(connectionFactory, serializer)
    }
}
