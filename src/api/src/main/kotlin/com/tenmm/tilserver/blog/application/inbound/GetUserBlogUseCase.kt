package com.tenmm.tilserver.blog.application.inbound

import com.tenmm.tilserver.blog.application.inbound.model.GetBlogResult

interface GetUserBlogUseCase {
    fun getAllByUserName(name: String): List<GetBlogResult>
}
