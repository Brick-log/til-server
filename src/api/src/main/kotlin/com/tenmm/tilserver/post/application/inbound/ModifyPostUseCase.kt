package com.tenmm.tilserver.post.application.inbound

import com.tenmm.tilserver.common.domain.OperationResult
import com.tenmm.tilserver.post.application.inbound.model.ModifyPostCommand

interface ModifyPostUseCase {
    fun modifyByIdentifier(command: ModifyPostCommand): OperationResult
}
