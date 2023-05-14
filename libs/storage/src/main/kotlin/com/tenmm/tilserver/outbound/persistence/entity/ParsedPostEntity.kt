package com.tenmm.tilserver.outbound.persistence.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.sql.Timestamp

@Table(name = "parsed_post")
@Entity
data class ParsedPostEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column
    val identifier: String,

    @Column
    val userIdentifier: String,

    @Column
    val url: String,

    @Column
    val title: String,

    @Column
    val description: String?,

    @Column(name = "createdAt")
    val createdAt: Timestamp?,
)
