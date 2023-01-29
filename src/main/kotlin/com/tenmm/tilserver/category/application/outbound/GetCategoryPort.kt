package com.tenmm.tilserver.category.application.outbound

import com.tenmm.tilserver.category.domain.Category

interface GetCategoryPort {
    fun getAll(): List<Category>
    fun getByIdentifier(identifier: String): Category?
}
