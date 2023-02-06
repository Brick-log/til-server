package com.tenmm.tilserver.blog.application.inbound

import com.tenmm.tilserver.blog.domain.BlogPlatformType

interface GetBlogPlatformUseCase {
    fun getAll(): List<BlogPlatformType>
    fun getByUrl(url: String): BlogPlatformType
}
