package com.tenmm.tilserver.blog.application.service

import com.tenmm.tilserver.blog.application.inbound.GetUserBlogUseCase
import com.tenmm.tilserver.blog.application.inbound.model.GetBlogResult
import org.springframework.stereotype.Service

@Service
class GetUserBlogService : GetUserBlogUseCase {
    override fun getAllByUserName(name: String): List<GetBlogResult> {
        return listOf()
    }
}
