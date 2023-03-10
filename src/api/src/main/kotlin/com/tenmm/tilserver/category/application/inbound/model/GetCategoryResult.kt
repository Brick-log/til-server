package com.tenmm.tilserver.category.application.inbound.model

import com.tenmm.tilserver.common.domain.Identifier

data class GetCategoryResult(
    val identifier: Identifier,
    val name: String,
)
