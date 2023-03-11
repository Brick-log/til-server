package com.tenmm.tilserver.category.adapter.inbound.rest.model

data class GetCategoriesResponse(
    val categories: List<CategoryResult>,
)

data class CategoryResult(
    val identifier: String,
    val name: String,
)
