package com.tenmm.tilserver.user.application.inbound

import com.tenmm.tilserver.user.domain.User

interface GetUserUseCase {
    fun getByName(name: String): User
}
