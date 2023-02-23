package com.tenmm.tilserver.post.application.service

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.common.domain.OperationResult
import com.tenmm.tilserver.post.application.inbound.AddRecommendedPostUseCase
import com.tenmm.tilserver.post.application.inbound.DeleteRecommendedPostUseCase
import com.tenmm.tilserver.post.application.inbound.GetRecommendedPostUseCase
import com.tenmm.tilserver.post.application.inbound.model.GetPostListResult
import org.springframework.stereotype.Service

@Service
class RecommendedPostService : GetRecommendedPostUseCase, AddRecommendedPostUseCase, DeleteRecommendedPostUseCase {
    override fun addByPostId(postIdentifier: Identifier): OperationResult {
        TODO("Not yet implemented")
    }

    override fun deleteByPostId(postIdentifier: Identifier): OperationResult {
        TODO("Not yet implemented")
    }

    override fun getRecommendedPostListRandom(): GetPostListResult {
        TODO("Not yet implemented")
    }

    override fun getRecommendedPostListByCategory(categoryIdentifier: Identifier): GetPostListResult {
        TODO("Not yet implemented")
    }
}
