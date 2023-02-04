package com.tenmm.tilserver.common.domain

import com.tenmm.tilserver.common.utils.UUID_REGEX

data class Identifier(
    val value: String,
) {
    init {
        if (!UUID_REGEX.matches(value)) {
            throw IllegalArgumentException("Invalid identifier: $value")
        }
    }
}
