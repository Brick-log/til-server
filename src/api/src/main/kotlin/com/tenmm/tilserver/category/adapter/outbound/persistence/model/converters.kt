package com.tenmm.tilserver.category.adapter.outbound.persistence.model

import com.tenmm.tilserver.category.domain.Category
import com.tenmm.tilserver.common.domain.Identifier

fun CategoryEntity.toDomain(): Category {
    return Category(
        identifier = Identifier(identifier),
        name = name
    )
}
