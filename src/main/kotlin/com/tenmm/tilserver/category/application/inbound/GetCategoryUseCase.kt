package com.tenmm.tilserver.category.application.inbound

import com.tenmm.tilserver.category.domain.Category

interface GetCategoryUseCase {
    fun getAll(): List<Category>
    fun getByIdentifier(identifier: String): Category
}
