package com.tenmm.tilserver.category.adapter.outbound.persistence.model

data class CategoryEntity(
    val id: Long,
    val identifier: String,
    val name: String,
    val createdAt: Long,
)
