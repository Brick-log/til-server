package com.tenmm.tilserver.crawler.domain

import java.sql.Timestamp

data class Post(
    val title: String,
    val createdAt: Timestamp?,
    val description: String,
    val url: Url,
)
