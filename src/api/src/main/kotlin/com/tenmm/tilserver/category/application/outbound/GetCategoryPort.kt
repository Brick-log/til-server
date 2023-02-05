package com.tenmm.tilserver.category.application.outbound

import com.tenmm.tilserver.category.domain.Category
import com.tenmm.tilserver.common.domain.Identifier

interface GetCategoryPort {
    fun getAll(): List<Category>
    fun getByIdentifier(identifier: Identifier): Category?
}
