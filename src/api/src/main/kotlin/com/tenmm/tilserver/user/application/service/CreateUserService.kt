package com.tenmm.tilserver.user.application.service

import com.tenmm.tilserver.common.config.properties.ProfileImgProperties
import com.tenmm.tilserver.common.domain.OperationResult
import com.tenmm.tilserver.user.application.inbound.CreateUserUseCase
import com.tenmm.tilserver.user.application.inbound.model.CreateUserCommand
import com.tenmm.tilserver.user.application.outbound.SaveUserPort
import com.tenmm.tilserver.user.application.outbound.model.CreateUserRequest
import org.springframework.stereotype.Service

@Service
class CreateUserService(
    private val profileImgProperties: ProfileImgProperties,
    private val saveUserPort: SaveUserPort,
) : CreateUserUseCase {
    override fun create(command: CreateUserCommand): OperationResult {
        val request = CreateUserRequest(
            userIdentifier = command.userIdentifier,
            name = command.name,
            profileImgSrc = profileImgProperties.default
        )
        val user = saveUserPort.save(request)
        return if (user != null) OperationResult.success() else OperationResult.fail()
    }
}
