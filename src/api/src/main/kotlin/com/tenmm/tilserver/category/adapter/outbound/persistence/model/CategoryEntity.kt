package com.tenmm.tilserver.category.adapter.outbound.persistence.model

import java.sql.Timestamp
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import com.tenmm.tilserver.common.domain.Identifier
import java.sql.Timestamp

@Entity(name = "categories")
data class CategoryEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @Column(unique = true)
    val identifier: Identifier,
    @Column(length = 2000)
    val name: String,
    @Column
    val createdAt: Timestamp,
)
