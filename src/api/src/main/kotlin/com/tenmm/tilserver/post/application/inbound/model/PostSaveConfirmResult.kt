package com.tenmm.tilserver.post.application.inbound.model

import com.tenmm.tilserver.common.domain.OperationResult

data class PostSaveConfirmResult(
    val operationResult: OperationResult,
    val monthlyPublishCount: Int,
)
