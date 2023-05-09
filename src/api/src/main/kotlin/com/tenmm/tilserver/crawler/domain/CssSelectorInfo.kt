package com.tenmm.tilserver.crawler.domain

data class CssSelectorInfo(
    val titleCssSelector: String,
    val descriptionCssSelector: String,
    val createdAtCssSelector: String,
    val dateFormat: String,
)
