package com.tenmm.tilserver.user.application.outbound

import com.tenmm.tilserver.user.application.inbound.model.CreateUserCommand
import com.tenmm.tilserver.user.domain.User

interface SaveUserPort {
    fun save(command: CreateUserCommand): User?
}
