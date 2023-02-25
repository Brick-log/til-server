package com.tenmm.tilserver.user.adapter.outbound.persistence.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class UserEntity(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @field:Column
    val name: String,

    @field:Column
    val userIdentifier: String,

    @field:Column
    val categoryIdentifier: String,

    @field:Column
    val introduction: String,

    @field:Column
    val profileImgSrc: String,

    @field:Column
    val path: String,

    @field:Column
    @field:Enumerated(EnumType.STRING)
    val status: UserStatus
)
