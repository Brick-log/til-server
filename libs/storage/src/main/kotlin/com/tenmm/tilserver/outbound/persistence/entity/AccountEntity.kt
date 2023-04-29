package com.tenmm.tilserver.outbound.persistence.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.sql.Timestamp
import java.time.Instant

@Table(name = "account")
@Entity
data class AccountEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column
    val userIdentifier: String,

    @Column
    @Enumerated(EnumType.STRING)
    val oAuthType: OAuthType,

    @Column
    val email: String,

    @Column
    val isSpamNotificationAgreed: Boolean,

    @Column
    @Enumerated(EnumType.STRING)
    val status: AccountStatus,

    @Column
    val createdAt: Timestamp = Timestamp.from(Instant.now()),
)

enum class OAuthType {
    GOOGLE
}

enum class AccountStatus {
    BLOCK, ACTIVE
}
