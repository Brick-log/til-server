package com.tenmm.tilserver.common.adapter.outbound

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.tenmm.tilserver.common.security.AES128
import kotlin.reflect.KClass
import org.springframework.stereotype.Component

@Component
class PageTokenConverter(
    private val aes128: AES128,
) {

    private val objectMapper = jacksonObjectMapper()
    fun encryptPageToken(pageTokenObject: Any): String {
        return aes128.encrypt(objectMapper.writeValueAsString(pageTokenObject))
    }

    fun <T : Any> decryptPageToken(value: String, classType: KClass<T>): T {
        return objectMapper.readValue(aes128.decrypt(value), classType.java)
    }
}
