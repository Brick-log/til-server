package com.tenmm.tilserver.post.application.inbound

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.common.domain.OperationResult

interface DeletePostUseCase {
    fun deleteByIdentifier(identifier: Identifier): OperationResult
}
