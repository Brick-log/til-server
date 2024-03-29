package com.tenmm.tilserver.category.adapter.outbound

import com.tenmm.tilserver.category.domain.Category
import com.tenmm.tilserver.outbound.persistence.entity.CategoryEntity

fun CategoryEntity.toDomain(): Category {
    return Category(
        identifier = this.identifier,
        name = this.name
    )
}
