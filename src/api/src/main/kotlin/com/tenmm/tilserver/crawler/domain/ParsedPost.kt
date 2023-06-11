package com.tenmm.tilserver.crawler.domain

import com.tenmm.tilserver.common.domain.Url
import java.sql.Timestamp

data class ParsedPost(
    val url: Url,
    val title: String,
    val description: String,
    val createdAt: Timestamp?,
)
