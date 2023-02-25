package com.tenmm.tilserver.user.adapter.outbound.persistence.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDateTime

@Entity
class AccountEntity(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @field:Column
    val oAuthIdentifier: String,

    @field:Column
    val userIdentifier: String,

    @field:Column
    @field:Enumerated(EnumType.STRING)
    val oAuthType: OAuthType,

    @field:Column
    val oAuthToken: String,

    @field:Column
    val isSpamNotificationAgreed: Boolean,

    @field:Column
    @field:Enumerated(EnumType.STRING)
    val status: AccountStatus,

    @field:Column
    val createdAt: LocalDateTime
)
