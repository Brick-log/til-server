package com.tenmm.tilserver.user.application.service

import com.tenmm.tilserver.user.application.inbound.CreateUserUseCase
import com.tenmm.tilserver.user.application.inbound.model.CreateUserCommand
import com.tenmm.tilserver.user.domain.User
import org.springframework.stereotype.Service

@Service
class CreateUserService : CreateUserUseCase {
    override fun create(command: CreateUserCommand): User {
        TODO("Not yet implemented")
    }
}
