package com.tenmm.tilserver.post.adapter.inbound.rest.model

import com.tenmm.tilserver.post.application.inbound.model.PostSaveConfirmResult

data class ConfirmUploadPostResponse(
    val isSuccess: Boolean,
    val monthlyPublishCount: Int,
    val month: Int,
    val year: Int,
) {
    companion object {
        fun fromResult(result: PostSaveConfirmResult): ConfirmUploadPostResponse {
            return ConfirmUploadPostResponse(
                isSuccess = result.operationResult.isSuccess,
                monthlyPublishCount = result.monthlyPublishCount,
                month = result.month,
                year = result.year
            )
        }
    }
}
