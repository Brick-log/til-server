package com.tenmm.tilserver.category.adapter.inbound.rest.model

import com.tenmm.tilserver.category.domain.Category

data class GetCategoriesResponse(
    val categories: List<CategoryResult>,
)

data class CategoryResult(
    val identifier: String,
    val name: String,
)

fun Category.toResult(): CategoryResult {
    return CategoryResult(
        identifier = this.identifier,
        name = this.name
    )
}
