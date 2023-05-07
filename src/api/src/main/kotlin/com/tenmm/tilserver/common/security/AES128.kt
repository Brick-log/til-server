package com.tenmm.tilserver.common.security

import java.lang.RuntimeException
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets
import java.util.Base64
import java.util.Optional
import java.util.function.Predicate
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec
import org.springframework.stereotype.Component

@Component
class AES128(
    securityProperties: SecurityProperties,
) {
    private val keyBytes = securityProperties.key.toByteArray(ENCODING_TYPE)
    private val secretKeySpec = SecretKeySpec(keyBytes, "AES")
    private val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
    private val ivParameterSpec = IvParameterSpec(keyBytes)

    companion object {
        private val ENCODING_TYPE: Charset = StandardCharsets.UTF_8
    }

    init {
        validation(securityProperties.key)
    }

    fun encrypt(str: String): String {
        try {
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec)
            val encrypted: ByteArray = cipher.doFinal(str.toByteArray(ENCODING_TYPE))
            return String(Base64.getEncoder().encode(encrypted), ENCODING_TYPE)
        } catch (e: Exception) {
            throw RuntimeException("Encrypt Fail: $str")
        }
    }

    fun decrypt(str: String): String {
        try {
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec)
            val decoded: ByteArray = Base64.getDecoder().decode(str.toByteArray(ENCODING_TYPE))
            return String(cipher.doFinal(decoded), ENCODING_TYPE)
        } catch (e: Exception) {
            throw RuntimeException("Decrypt Fail: $str")
        }
    }

    private fun validation(key: String) {
        Optional.ofNullable(key)
            .filter(Predicate.not { obj: String -> obj.isBlank() })
            .filter(Predicate.not { s -> s.length != 16 })
            .orElseThrow { IllegalArgumentException("Validation Fail: $key") }
    }
}
