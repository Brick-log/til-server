package com.tenmm.tilserver.user.application.inbound

import com.tenmm.tilserver.common.domain.OperationResult
import com.tenmm.tilserver.user.application.inbound.model.CreateUserCommand

interface CreateUserUseCase {
    fun create(command: CreateUserCommand): OperationResult
}
