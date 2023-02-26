package com.tenmm.tilserver.outbound.persistence.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime

@Table(name = "post")
@Entity
data class PostEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column
    val categoryIdentifier: String,

    @Column
    val userIdentifier: String,

    @Column
    val url: String,

    @Column
    val title: String,

    @Column
    val description: String,

    @Column
    val hitCount: Int,

    @Column
    val createdAt: LocalDateTime,
)
