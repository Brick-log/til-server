package com.tenmm.tilserver.outbound.persistence.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Table(name = "user")
@Entity
data class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column
    val name: String,

    @Column
    val userIdentifier: String,

    @Column
    val categoryIdentifier: String,

    @Column
    val introduction: String,

    @Column
    val profileImgSrc: String,

    @Column
    val path: String,

    @Column
    @Enumerated(EnumType.STRING)
    val status: UserStatus,
)

enum class UserStatus {
    ON_BOARDING,
    COMPLETED
}
