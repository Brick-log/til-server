package com.tenmm.tilserver.user.application.inbound

import com.tenmm.tilserver.user.application.inbound.model.CreateUserCommand
import com.tenmm.tilserver.user.domain.User

interface CreateUserUseCase {
    fun create(command: CreateUserCommand): User
}
