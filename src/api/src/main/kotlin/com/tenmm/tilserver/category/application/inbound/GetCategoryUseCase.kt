package com.tenmm.tilserver.category.application.inbound

import com.tenmm.tilserver.category.domain.Category
import com.tenmm.tilserver.common.domain.Identifier

interface GetCategoryUseCase {
    fun getAll(): List<Category>
    fun getByIdentifier(identifier: Identifier): Category
}
