package com.tenmm.tilserver.category.domain

import com.tenmm.tilserver.common.domain.Identifier

data class Category(
    val identifier: Identifier,
    val name: String,
)
