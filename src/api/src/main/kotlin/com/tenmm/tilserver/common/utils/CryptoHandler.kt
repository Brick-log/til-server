package com.tenmm.tilserver.common.utils

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.tenmm.tilserver.common.security.AES128
import kotlin.reflect.KClass
import org.springframework.stereotype.Component

@Component
class CryptoHandler(
    private val aes128: AES128,
) {

    private val objectMapper = jacksonObjectMapper()
    fun encrypt(target: Any): String {
        return aes128.encrypt(objectMapper.writeValueAsString(target))
    }

    fun <T : Any> decrypt(value: String, classType: KClass<T>): T {
        return objectMapper.readValue(aes128.decrypt(value), classType.java)
    }
}
