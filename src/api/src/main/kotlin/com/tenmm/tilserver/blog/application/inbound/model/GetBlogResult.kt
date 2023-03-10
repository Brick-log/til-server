package com.tenmm.tilserver.blog.application.inbound.model

import com.tenmm.tilserver.blog.domain.BlogPlatformType

data class GetBlogResult(
    val platform: BlogPlatformType,
    val blogIdentifier: String,
    val url: String
)
