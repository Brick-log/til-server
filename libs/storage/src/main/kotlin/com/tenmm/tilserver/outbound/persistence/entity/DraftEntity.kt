package com.tenmm.tilserver.outbound.persistence.entity

import com.tenmm.tilserver.common.utils.getNowTimestamp
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.sql.Timestamp

@Table(name = "draft")
@Entity
data class DraftEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column
    val userIdentifier: String,

    @Column
    val data: String,

    @Column
    val updatedAt: Timestamp = getNowTimestamp(),
)
