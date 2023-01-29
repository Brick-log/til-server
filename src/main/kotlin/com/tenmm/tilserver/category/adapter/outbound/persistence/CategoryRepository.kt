package com.tenmm.tilserver.category.adapter.outbound.persistence

import com.tenmm.tilserver.category.adapter.outbound.persistence.model.CategoryEntity

interface CategoryRepository {
    fun findAll(): List<CategoryEntity>
    fun findByIdentifier(identifier: String): CategoryEntity?
}
