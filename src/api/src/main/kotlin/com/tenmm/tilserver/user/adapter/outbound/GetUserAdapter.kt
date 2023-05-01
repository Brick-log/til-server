package com.tenmm.tilserver.user.adapter.outbound

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.outbound.persistence.repository.UserRepository
import com.tenmm.tilserver.user.application.outbound.GetUserPort
import com.tenmm.tilserver.user.domain.User
import org.springframework.stereotype.Component

@Component
class GetUserAdapter(
    private val userRepository: UserRepository,
) : GetUserPort {
    override fun getByUserIdentifier(identifier: Identifier): User? {
        return userRepository.findByUserIdentifier(identifier.value)?.toModel()
    }

    override fun getByName(name: String): User? {
        return userRepository.findByName(name)?.toModel()
    }

    override fun getByPath(path: String): User? {
        return userRepository.findByPath(path)?.toModel()
    }
}
