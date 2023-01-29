package com.tenmm.tilserver.category.adapter.inbound.model

data class GetCategoriesResponse(
    val categories: List<Category>,
)

data class Category(
    val id: String,
    val name: String,
    val description: String,
)
