package com.tenmm.tilserver.user.application.service

import com.tenmm.tilserver.common.domain.OperationResult
import com.tenmm.tilserver.user.application.inbound.CreateUserUseCase
import com.tenmm.tilserver.user.application.inbound.model.CreateUserCommand
import com.tenmm.tilserver.user.application.outbound.SaveUserPort
import org.springframework.stereotype.Service

@Service
class CreateUserService(
    private val saveUserPort: SaveUserPort
) : CreateUserUseCase {
    override fun create(command: CreateUserCommand): OperationResult {
        val user = saveUserPort.save(command)
        return if (user != null) OperationResult.success() else OperationResult.fail()
    }
}
