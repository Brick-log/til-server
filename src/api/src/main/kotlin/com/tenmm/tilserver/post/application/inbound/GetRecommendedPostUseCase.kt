package com.tenmm.tilserver.post.application.inbound

import com.tenmm.tilserver.post.application.inbound.model.GetPostListResult

interface GetRecommendedPostUseCase {
    fun getRecommendedPostListRandom(): GetPostListResult
    fun getRecommendedPostListByCategory(categoryIdentifier: String): GetPostListResult
}
