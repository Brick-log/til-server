package com.tenmm.tilserver.blog.application.service

import com.tenmm.tilserver.blog.application.inbound.GetBlogPlatformUseCase
import com.tenmm.tilserver.blog.domain.BlogPlatformType
import org.springframework.stereotype.Service

@Service
class GetBlogPlatformService : GetBlogPlatformUseCase {
    override fun getAll() = BlogPlatformType.getAll()
    override fun getByUrl(url: String): BlogPlatformType = BlogPlatformType.get(url)
}
