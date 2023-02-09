package com.tenmm.tilserver.user.application.inbound

import com.tenmm.tilserver.user.domain.User

interface GetUserUseCase {
    fun getByPath(path: String): User
}
