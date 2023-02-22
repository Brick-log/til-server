package com.tenmm.tilserver.user.adapter.outbound.persistence.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Lob

@Entity
class User(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @field:Column(unique = true)
    val name: String,

    @field:Column(unique = true)
    val userIdentifier: String,

    @field:Column
    val categoryIdentifier: String,

    @field:Lob
    @field:Column
    val introduction: String,

    @field:Column
    val profileImgSrc: String,

    @field:Column(unique = true)
    val path: String,

    @field:Column
    @field:Enumerated(EnumType.STRING)
    val status: UserStatus
)
