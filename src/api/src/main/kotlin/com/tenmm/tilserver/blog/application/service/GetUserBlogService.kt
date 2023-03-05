package com.tenmm.tilserver.blog.application.service

import com.tenmm.tilserver.blog.application.inbound.GetUserBlogUseCase
import com.tenmm.tilserver.blog.application.inbound.model.GetBlogResult
import com.tenmm.tilserver.blog.domain.BlogPlatformType
import com.tenmm.tilserver.common.domain.Identifier
import org.springframework.stereotype.Service

@Service
class GetUserBlogService : GetUserBlogUseCase {
    override fun getAllByUserName(name: String): List<GetBlogResult> {
        return listOf(
            GetBlogResult(
                platform = BlogPlatformType.VELOG,
                blogIdentifier = Identifier.generate(),
                url = "https://velog.io/"
            )
        )
    }
}
