package com.tenmm.tilserver.user.application.inbound

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.user.domain.User

interface GetUserUseCase {
    fun getByPath(path: String): User
    fun getByIdentifier(userIdentifier: Identifier): User
}
