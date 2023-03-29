package com.tenmm.crawler.domain

import java.sql.Timestamp

data class Crawling(
    val title: String,
    val createdAt: Timestamp?,
    val description: String,
    val url: String,
)
