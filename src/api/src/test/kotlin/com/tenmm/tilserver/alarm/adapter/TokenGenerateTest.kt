package com.tenmm.tilserver.alarm.adapter

import com.tenmm.tilserver.auth.adapter.outbound.jwt.GenerateTokenAdapter
import com.tenmm.tilserver.auth.adapter.outbound.jwt.ResolveTokenAdapter
import com.tenmm.tilserver.auth.adapter.outbound.jwt.config.JwtConfigProperties
import com.tenmm.tilserver.auth.domain.TokenType
import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.common.security.AES128
import com.tenmm.tilserver.common.security.SecurityProperties
import com.tenmm.tilserver.common.utils.CryptoHandler
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TokenGenerateTest {
    private val securityProperties = SecurityProperties("1q2w3e4r5t6y7u8i")
    private val ASE128 = AES128(securityProperties)
    private val cryptoHandler = CryptoHandler(ASE128)
    private val jwtConfigProperties = JwtConfigProperties(
        access = JwtConfigProperties.TokenProperties(
            86400000,
            "5f95a5f5c5b5a5a5958f8f7f7e7e7d7d7c6b6a696867666564636261605f"
        ),
        refresh = JwtConfigProperties.TokenProperties(
            86400000,
            "5f95a5f5c5b5a5a5958f8f7f7e7e7d7d7c6b6a696867666564636261605f"
        )
    )
    val generateTokenAdapter: GenerateTokenAdapter = GenerateTokenAdapter(
        cryptoHandler = cryptoHandler,
        jwtConfigProperties = jwtConfigProperties
    )

    val resolveTokenAdapter = ResolveTokenAdapter(
        cryptoHandler = cryptoHandler,
        jwtConfigProperties = jwtConfigProperties
    )

    @Test
    fun generateTokenTest() {
        val id = Identifier.generate()

        val accessToken = generateTokenAdapter.generateToken(id.value, TokenType.ACCESS)

        println(id)
        println(accessToken)
        val resolvedValue = resolveTokenAdapter.resolveToken(accessToken, TokenType.ACCESS)

        assertThat(id.value).isEqualTo(resolvedValue)
    }
}
