package com.tenmm.tilserver.common.domain

import com.tenmm.tilserver.common.constants.URL_REGEX

data class Url(
    val value: String,
) {
    init {
        if (value != "" && !URL_REGEX.matches(value)) {
            throw InvalidUrlException("Invalid URL : $value")
        }
    }
}

fun String.toUrl(): Url {
    return Url(this)
}
