package com.tenmm.tilserver.user.application.service

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.common.domain.NotFoundException
import com.tenmm.tilserver.user.application.inbound.GetUserUseCase
import com.tenmm.tilserver.user.application.outbound.GetUserPort
import com.tenmm.tilserver.user.domain.User
import org.springframework.stereotype.Service

@Service
class GetUserService(
    private val getUserPort: GetUserPort,
) : GetUserUseCase {
    override fun getByName(name: String): User {
        return getUserPort.getByName(name) ?: throw NotFoundException("")
    }

    override fun getByPath(path: String): User {
        return getUserPort.getByPath(path) ?: throw NotFoundException("")
    }

    override fun getByIdentifier(userIdentifier: Identifier): User {
        return getUserPort.getByUserIdentifier(userIdentifier) ?: throw NotFoundException("")
    }

    override fun getByIdentifierList(userIdentifiers: List<Identifier>): List<User> {
        return userIdentifiers.map {
            getUserPort.getByUserIdentifier(it) ?: throw NotFoundException("")
        }
    }
}
