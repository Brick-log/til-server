package com.tenmm.tilserver.blog.application.inbound.model

import com.tenmm.tilserver.blog.domain.BlogPlatformType
import com.tenmm.tilserver.common.domain.Identifier

data class GetBlogResult(
    val platform: BlogPlatformType,
    val blogIdentifier: Identifier,
    val url: String
)
