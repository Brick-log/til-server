package com.tenmm.tilserver.category.adapter.inbound.rest.model

import com.tenmm.tilserver.category.domain.Category

data class GetCategoriesResponse(
    val categories: List<Category>,
)
