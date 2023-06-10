package com.tenmm.tilserver.post.application.service

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.common.domain.ModifyPostFailException
import com.tenmm.tilserver.common.domain.ModifyPostFailType
import com.tenmm.tilserver.common.domain.OperationResult
import com.tenmm.tilserver.post.application.inbound.DeletePostUseCase
import com.tenmm.tilserver.post.application.inbound.ModifyPostUseCase
import com.tenmm.tilserver.post.application.inbound.model.ModifyPostCommand
import com.tenmm.tilserver.post.application.outbound.DeletePostPort
import com.tenmm.tilserver.post.application.outbound.ModifyPostPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ModifyPostService(
    private val deletePostPort: DeletePostPort,
    private val modifyPostPort: ModifyPostPort,
) : ModifyPostUseCase, DeletePostUseCase {
    override fun deleteByIdentifier(postIdentifier: Identifier): OperationResult {
        return OperationResult(deletePostPort.deleteByIdentifier(postIdentifier))
    }

    @Transactional
    override fun modifyByIdentifier(command: ModifyPostCommand): OperationResult {
        modifyPostPort.updateTitle(command.identifier, command.title).check(UpdatePostType.TITLE)
        modifyPostPort.updateSummary(command.identifier, command.summary).check(UpdatePostType.SUMMARY)
        modifyPostPort.updateCreatedAt(command.identifier, command.createdAt).check(UpdatePostType.CREATED_AT)
        return OperationResult.success()
    }

    enum class UpdatePostType {
        TITLE, SUMMARY, CREATED_AT;

        fun toErrorCode(): ModifyPostFailType {
            return when (this) {
                TITLE -> ModifyPostFailType.POST_TITLE
                SUMMARY -> ModifyPostFailType.POST_SUMMARY
                CREATED_AT -> ModifyPostFailType.POST_CREATED_AT
            }
        }
    }

    private fun OperationResult.check(type: UpdatePostType) {
        if (!this.isSuccess) {
            throw ModifyPostFailException(type.toErrorCode())
        }
    }
}
