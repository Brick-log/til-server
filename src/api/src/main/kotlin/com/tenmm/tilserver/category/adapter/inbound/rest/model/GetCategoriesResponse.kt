package com.tenmm.tilserver.category.adapter.inbound.rest.model

import com.tenmm.tilserver.category.application.inbound.model.GetCategoryResult

data class GetCategoriesResponse(
    val categories: List<GetCategoryResult>,
)
