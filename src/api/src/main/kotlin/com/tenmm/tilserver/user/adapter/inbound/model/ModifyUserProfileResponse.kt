package com.tenmm.tilserver.user.adapter.inbound.model

import com.tenmm.tilserver.common.domain.OperationResult

data class ModifyUserProfileResponse(
    val isSuccess: Boolean,
) {
    companion object {
        fun fromResult(result: OperationResult): ModifyUserProfileResponse {
            return ModifyUserProfileResponse(
                isSuccess = result.isSuccess
            )
        }
    }
}
