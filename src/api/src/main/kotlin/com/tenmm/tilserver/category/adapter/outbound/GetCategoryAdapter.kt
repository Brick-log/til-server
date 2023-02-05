package com.tenmm.tilserver.category.adapter.outbound

import com.tenmm.tilserver.category.adapter.outbound.persistence.CategoryRepository
import com.tenmm.tilserver.category.adapter.outbound.persistence.model.toDomain
import com.tenmm.tilserver.category.application.outbound.GetCategoryPort
import com.tenmm.tilserver.category.domain.Category
import com.tenmm.tilserver.common.domain.Identifier

class GetCategoryAdapter(
    private val categoryRepository: CategoryRepository,
) : GetCategoryPort {
    override fun getAll(): List<Category> {
        return categoryRepository.findAll().map { it.toDomain() }
    }

    override fun getByIdentifier(identifier: Identifier): Category? {
        return categoryRepository.findByIdentifier(identifier.value)?.toDomain()
    }
}
