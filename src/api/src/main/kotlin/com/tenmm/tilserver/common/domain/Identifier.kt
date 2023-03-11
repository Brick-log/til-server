package com.tenmm.tilserver.common.domain

import com.tenmm.tilserver.common.constants.UUID_REGEX
import java.util.UUID

data class Identifier(
    val value: String,
) {
    init {
        if (!UUID_REGEX.matches(value)) {
            throw IllegalArgumentException("Invalid identifier: $value")
        }
    }

    companion object {
        fun generate() = Identifier(UUID.randomUUID().toString())
    }
}
