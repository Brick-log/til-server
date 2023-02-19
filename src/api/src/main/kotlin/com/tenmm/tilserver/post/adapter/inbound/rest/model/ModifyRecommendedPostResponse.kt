package com.tenmm.tilserver.post.adapter.inbound.rest.model

import com.tenmm.tilserver.common.domain.OperationResult

data class ModifyRecommendedPostResponse(
    val isSuccess: Boolean,
) {
    companion object {
        fun fromResult(operationResult: OperationResult): ModifyRecommendedPostResponse {
            return ModifyRecommendedPostResponse(isSuccess = operationResult.isSuccess)
        }
    }
}
