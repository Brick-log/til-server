package com.tenmm.crawler.post.domain

data class Url(
    val value: String,
) {
    private val URL_REGEX_VALUE =
        "[(http(s)?):\\/\\/(www\\.)?a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)"
    private val URL_REGEX = URL_REGEX_VALUE.toRegex()

    init {
        if (!URL_REGEX.matches(value)) {
            throw IllegalArgumentException("Invalid URL : $value")
        }
    }
}
