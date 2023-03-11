package com.tenmm.tilserver.post.application.service

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.common.domain.OperationResult
import com.tenmm.tilserver.post.application.inbound.DeletePostUseCase
import com.tenmm.tilserver.post.application.inbound.ModifyPostUseCase
import com.tenmm.tilserver.post.application.inbound.model.ModifyPostCommand
import com.tenmm.tilserver.post.application.outbound.DeletePostPort
import com.tenmm.tilserver.post.application.outbound.ModifyPostPort
import org.springframework.stereotype.Service

@Service
class ModifyPostService(
    private val deletePostPort: DeletePostPort,
    private val modifyPostPort: ModifyPostPort,
) : ModifyPostUseCase, DeletePostUseCase {
    override fun deleteByIdentifier(postIdentifier: Identifier): OperationResult {
        return OperationResult(deletePostPort.deleteByIdentifier(postIdentifier))
    }

    override fun modifyByIdentifier(command: ModifyPostCommand): OperationResult {
        return OperationResult(modifyPostPort.modifyByIdentifier(command))
    }
}
