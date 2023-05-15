package com.tenmm.tilserver.outbound.persistence.repository

import com.tenmm.tilserver.outbound.persistence.entity.TokenEntity
import org.springframework.data.jpa.repository.JpaRepository

interface TokenRepository : JpaRepository<TokenEntity, Long> {
    fun findByUserIdentifierAndAccessToken(
        userIdentifier: String,
        accessToken: String
    ): TokenEntity?
}