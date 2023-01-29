package com.tenmm.tilserver.category.adapter.outbound.persistence.model

import com.tenmm.tilserver.category.domain.Category

fun CategoryEntity.toDomain(): Category {
    return Category(
        identifier = identifier,
        name = name
    )
}
