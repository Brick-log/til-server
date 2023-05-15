package com.tenmm.tilserver.outbound.persistence.repository

import com.tenmm.tilserver.outbound.persistence.entity.TokenEntity
import java.sql.Timestamp
import org.springframework.data.jpa.repository.JpaRepository

interface TokenRepository : JpaRepository<TokenEntity, Long> {
    fun findByUserIdentifierAndAccessTokenAndRefreshTokenExpireIsAfter(
        userIdentifier: String,
        accessToken: String,
        now:Timestamp
    ): TokenEntity?

    fun findByUserIdentifierAndAccessToken(
        userIdentifier: String,
        accessToken: String
    ): TokenEntity?

    fun deleteAllByRefreshTokenExpireBefore(now:Timestamp)
}