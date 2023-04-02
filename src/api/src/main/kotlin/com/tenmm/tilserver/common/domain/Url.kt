package com.tenmm.tilserver.common.domain

import com.tenmm.tilserver.common.constants.URL_REGEX

data class Url(
    val value: String,
) {
    init {
        if (!URL_REGEX.matches(value)) {
            throw IllegalArgumentException("Invalid URL : $value")
        }
    }
}
