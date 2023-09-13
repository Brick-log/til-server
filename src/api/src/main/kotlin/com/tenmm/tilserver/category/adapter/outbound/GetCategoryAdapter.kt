package com.tenmm.tilserver.category.adapter.outbound

import com.tenmm.tilserver.category.application.outbound.GetCategoryPort
import com.tenmm.tilserver.category.domain.Category
import com.tenmm.tilserver.outbound.persistence.repository.CategoryRepository
import org.springframework.stereotype.Component

@Component
class GetCategoryAdapter(
    private val categoryRepository: CategoryRepository,
) : GetCategoryPort {
    override fun getAll(): List<Category> {
        return categoryRepository.findAll().map { it.toDomain() }
    }

    override fun getByIdentifier(identifier: String): Category? {
        return categoryRepository.findByIdentifier(identifier)?.toDomain()
    }
}
