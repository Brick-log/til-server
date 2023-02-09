package com.tenmm.tilserver.post.application.inbound

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.post.application.inbound.model.GetPostListResult

interface GetRecommendedPostUseCase {
    fun getRecommendedPostListRandom(): GetPostListResult
    fun getRecommendedPostListByCategory(categoryIdentifier: Identifier): GetPostListResult
}
