package com.tenmm.tilserver.category.adapter.outbound.persistence

import com.tenmm.tilserver.category.adapter.outbound.persistence.model.CategoryEntity
import org.springframework.stereotype.Repository
import org.springframework.data.jpa.repository.JpaRepository

@Repository
interface CategoryRepository : JpaRepository<CategoryEntity, Long> {
    override fun findAll(): List<CategoryEntity>
    fun findByIdentifier(identifier: String): CategoryEntity?
}
