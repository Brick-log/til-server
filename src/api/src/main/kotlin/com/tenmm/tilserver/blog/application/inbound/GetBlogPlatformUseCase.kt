package com.tenmm.tilserver.blog.application.inbound

import com.tenmm.tilserver.blog.domain.BlogPlatformType
import com.tenmm.tilserver.common.domain.Url

interface GetBlogPlatformUseCase {
    fun getAll(): List<BlogPlatformType>
    fun getByUrl(url: Url): BlogPlatformType
}
