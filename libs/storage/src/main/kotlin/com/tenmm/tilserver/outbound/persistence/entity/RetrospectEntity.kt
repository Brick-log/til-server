package com.tenmm.tilserver.outbound.persistence.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.sql.Timestamp

@Table(name = "retrospect")
@Entity
data class RetrospectEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column
    val retrospectIdentifier: String,

    @Column
    val userIdentifier: String,

    @Column
    val categoryIdentifier: String,

    @Column
    val questionType: String,

    @Column
    val isSecret: Boolean,

    @Column
    val createdAt: Timestamp
)
