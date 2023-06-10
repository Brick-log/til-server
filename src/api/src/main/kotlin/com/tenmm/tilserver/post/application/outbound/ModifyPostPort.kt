package com.tenmm.tilserver.post.application.outbound

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.common.domain.OperationResult

interface ModifyPostPort {
    fun updateTitle(postIdentifier: Identifier, title: String): OperationResult
    fun updateSummary(postIdentifier: Identifier, summary: String): OperationResult
    fun updateCreatedAt(postIdentifier: Identifier, createdAt: Long): OperationResult
    fun increasePostHitCount(postIdentifier: Identifier)
}
