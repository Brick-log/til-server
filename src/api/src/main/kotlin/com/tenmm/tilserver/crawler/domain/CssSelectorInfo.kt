package com.tenmm.tilserver.crawler.domain

import java.text.SimpleDateFormat

data class CssSelectorInfo(
    val titleCssSelector: String,
    val descriptionCssSelector: String,
    val createdAtCssSelector: String,
    val dateFormat: SimpleDateFormat,
)
