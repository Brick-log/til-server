package com.tenmm.tilserver.post.application.inbound

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.post.application.inbound.model.GetPostListResult
import com.tenmm.tilserver.post.application.inbound.model.GetPostResult
import java.sql.Timestamp

interface GetPostUseCase {
    fun getPostByIdentifier(identifier: Identifier): GetPostResult
    fun getPostListByCategory(categoryIdentifier: Identifier): GetPostListResult
    fun getPostListByNameAndDate(name: String, to: Timestamp, from: Timestamp): GetPostListResult
}
