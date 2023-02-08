package com.tenmm.tilserver.post.application.inbound

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.common.domain.OperationResult

interface DeleteRecommendedPostUseCase {
    fun deleteByCategoryIdAndPostId(categoryIdentifier: Identifier, postIdentifier: Identifier): OperationResult
}
