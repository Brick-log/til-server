package com.tenmm.crawler.post.domain

import java.sql.Timestamp

data class Post(
    val title: String,
    val createdAt: Timestamp?,
    val description: String,
    val url: Url,
)
