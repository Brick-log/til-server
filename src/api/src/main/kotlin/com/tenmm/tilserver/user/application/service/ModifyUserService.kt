package com.tenmm.tilserver.user.application.service

import com.tenmm.tilserver.common.domain.NotFoundException
import com.tenmm.tilserver.common.domain.OperationResult
import com.tenmm.tilserver.user.application.inbound.ModifyUserUseCase
import com.tenmm.tilserver.user.application.inbound.model.ModifyUserCommand
import com.tenmm.tilserver.user.application.outbound.GetUserPort
import com.tenmm.tilserver.user.application.outbound.ModifyUserPort
import org.springframework.stereotype.Service

@Service
class ModifyUserService(
    private val getUserPort: GetUserPort,
    private val modifyUserPort: ModifyUserPort,
) : ModifyUserUseCase {
    override fun modifyUserInfo(command: ModifyUserCommand): OperationResult {
        getUserPort.getByUserIdentifier(command.userIdentifier)
            ?: throw NotFoundException("ZZ")

        modifyUserPort.modifyUserInfo(command)
        return OperationResult.success()
    }
}
