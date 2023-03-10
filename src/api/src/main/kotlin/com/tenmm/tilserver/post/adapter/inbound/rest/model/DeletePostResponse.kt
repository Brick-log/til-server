package com.tenmm.tilserver.post.adapter.inbound.rest.model

import com.tenmm.tilserver.common.domain.OperationResult

data class DeletePostResponse(
    val isSuccess: Boolean,
) {
    companion object {
        fun fromResult(operationResult: OperationResult): DeletePostResponse {
            return DeletePostResponse(isSuccess = operationResult.isSuccess)
        }
    }
}
