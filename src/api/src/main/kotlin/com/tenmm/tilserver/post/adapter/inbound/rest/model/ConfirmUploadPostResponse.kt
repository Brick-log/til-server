package com.tenmm.tilserver.post.adapter.inbound.rest.model

import com.tenmm.tilserver.common.domain.OperationResult

data class ConfirmUploadPostResponse(
    val isSuccess: Boolean,
) {
    companion object {
        fun fromResult(result: OperationResult): ConfirmUploadPostResponse {
            return ConfirmUploadPostResponse(result.isSuccess)
        }
    }
}
