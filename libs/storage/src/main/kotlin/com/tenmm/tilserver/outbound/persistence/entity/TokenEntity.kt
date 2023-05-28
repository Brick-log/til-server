package com.tenmm.tilserver.outbound.persistence.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.sql.Timestamp

@Entity
@Table(name = "token")
data class TokenEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column
    val userIdentifier: String,

    @Column
    val accessToken: String,

    @Column
    val refreshToken: String,

    @Column
    val refreshTokenExpire: Timestamp
)
