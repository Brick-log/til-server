package com.tenmm.tilserver.blog.application.inbound

import com.tenmm.tilserver.blog.application.inbound.model.GetBlogResult
import com.tenmm.tilserver.common.domain.Identifier

interface GetUserBlogUseCase {

    fun getAllByUserIdentifier(userIdentifier: Identifier): List<GetBlogResult>
}
