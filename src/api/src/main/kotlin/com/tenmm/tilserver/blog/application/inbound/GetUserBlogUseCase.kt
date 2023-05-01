package com.tenmm.tilserver.blog.application.inbound

import com.tenmm.tilserver.blog.application.inbound.model.GetBlogResult

interface GetUserBlogUseCase {
    fun getAllByUserPath(path: String): List<GetBlogResult>
}
