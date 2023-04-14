package com.tenmm.tilserver.common.adapter.outbound.rest

import feign.codec.Decoder
import feign.codec.Encoder
import feign.form.spring.SpringFormEncoder
import org.springframework.beans.factory.ObjectFactory
import org.springframework.boot.autoconfigure.ImportAutoConfiguration
import org.springframework.boot.autoconfigure.http.HttpMessageConverters
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.cloud.openfeign.FeignAutoConfiguration
import org.springframework.cloud.openfeign.support.SpringDecoder
import org.springframework.cloud.openfeign.support.SpringEncoder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableFeignClients("com.tenmm.tilserver")
@ImportAutoConfiguration(FeignAutoConfiguration::class)
class FeignConfig {

    @Bean
    fun feignFormEncoder(): Encoder {
        val messageConverters: ObjectFactory<HttpMessageConverters> =
            ObjectFactory<HttpMessageConverters> {
                val converters =
                    HttpMessageConverters()
                converters
            }
        return SpringFormEncoder(SpringEncoder(messageConverters))
    }

    @Bean
    fun feignFormDecoder(): Decoder {
        val messageConverters =
            ObjectFactory {
                val converters =
                    HttpMessageConverters()
                converters
            }
        return SpringDecoder(messageConverters)
    }
}
