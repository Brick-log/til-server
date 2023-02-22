package com.tenmm.tilserver.user.application.service

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.user.application.inbound.GetUserUseCase
import com.tenmm.tilserver.user.domain.User
import org.springframework.stereotype.Service

@Service
class GetUserService : GetUserUseCase {
    override fun getByPath(path: String): User {
        TODO("Not yet implemented")
    }

    override fun getByIdentifier(userIdentifier: Identifier): User {
        TODO("Not yet implemented")
    }
}
