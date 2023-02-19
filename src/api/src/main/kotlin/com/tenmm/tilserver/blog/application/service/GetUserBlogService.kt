package com.tenmm.tilserver.blog.application.service

import com.tenmm.tilserver.blog.application.inbound.GetUserBlogUseCase
import com.tenmm.tilserver.blog.application.inbound.model.GetBlogResult
import com.tenmm.tilserver.common.domain.Identifier
import org.springframework.stereotype.Service

@Service
class GetUserBlogService : GetUserBlogUseCase {
    override fun getAllByUserIdentifier(userIdentifier: Identifier): List<GetBlogResult> {
        return listOf()
    }
}
