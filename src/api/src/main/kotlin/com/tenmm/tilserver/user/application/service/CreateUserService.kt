package com.tenmm.tilserver.user.application.service

import com.tenmm.tilserver.common.config.properties.ProfileImgProperties
import com.tenmm.tilserver.common.domain.OperationResult
import com.tenmm.tilserver.user.application.inbound.CreateUserUseCase
import com.tenmm.tilserver.user.application.inbound.model.CreateUserCommand
import com.tenmm.tilserver.user.application.outbound.SaveUserPort
import com.tenmm.tilserver.user.application.outbound.model.CreateUserRequest
import java.time.LocalDateTime
import org.springframework.stereotype.Service

@Service
class CreateUserService(
    private val profileImgProperties: ProfileImgProperties,
    private val saveUserPort: SaveUserPort,
) : CreateUserUseCase {
    override fun create(command: CreateUserCommand): OperationResult {
        val path = pathGenerator()
        val request = CreateUserRequest(
            name = command.name,
            userIdentifier = command.userIdentifier,
            path = path,
            profileImgSrc = profileImgProperties.default,
            description = "${command.name}의 브릭로그입니다."
        )
        val user = saveUserPort.save(request)
        return if (user != null) OperationResult.success() else OperationResult.fail()
    }

    private fun pathGenerator(): String {
        val now = LocalDateTime.now()
        val month = now.month.value.toString().padStart(2, '0')
        val day = now.dayOfMonth.toString().padStart(2, '0')
        val hour = now.hour.toString().padStart(2, '0')
        val minute = now.minute.toString().padStart(2, '0')
        val second = now.second.toString().padStart(2, '0')

        return "user$second$month$hour$minute$day"
    }
}
