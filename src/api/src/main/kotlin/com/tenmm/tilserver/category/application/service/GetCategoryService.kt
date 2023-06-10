package com.tenmm.tilserver.category.application.service

import com.tenmm.tilserver.category.application.inbound.GetCategoryUseCase
import com.tenmm.tilserver.category.application.outbound.GetCategoryPort
import com.tenmm.tilserver.category.domain.Category
import com.tenmm.tilserver.common.domain.CategoryNotFoundException
import com.tenmm.tilserver.common.domain.Identifier
import org.springframework.stereotype.Service

@Service
class GetCategoryService(
    private val getCategoryPort: GetCategoryPort,
) : GetCategoryUseCase {
    override fun getAll(): List<Category> {
        return getCategoryPort.getAll()
    }

    override fun getByIdentifier(identifier: Identifier): Category {
        return getCategoryPort.getByIdentifier(identifier) ?: throw CategoryNotFoundException()
    }
}
