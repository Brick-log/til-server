package com.tenmm.tilserver.post.application.inbound

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.post.application.inbound.model.GetPostListResult
import com.tenmm.tilserver.post.application.inbound.model.GetPostMetaResult
import com.tenmm.tilserver.post.application.inbound.model.GetPostResult
import java.sql.Timestamp

interface GetPostUseCase {
    fun getPostByIdentifier(postIdentifier: Identifier): GetPostResult
    fun getPostListByCategory(categoryIdentifier: Identifier): GetPostListResult
    fun getPostListByNameAndDate(name: String, to: Timestamp, from: Timestamp): GetPostListResult
    fun getPostListByPageToken(pageToken: String): GetPostListResult

    fun getPostMetaListByNameAndDate(name: String, to: Timestamp, from: Timestamp): GetPostMetaResult
    fun getPostCountByMonth(name: String, month: Int): Int
}
