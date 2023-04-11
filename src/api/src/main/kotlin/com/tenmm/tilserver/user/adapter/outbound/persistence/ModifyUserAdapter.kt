package com.tenmm.tilserver.user.adapter.outbound.persistence

import com.tenmm.tilserver.outbound.persistence.repository.UserRepository
import com.tenmm.tilserver.user.application.outbound.SaveUserPort
import com.tenmm.tilserver.user.domain.User

class ModifyUserAdapter(
    private val userRepository: UserRepository
) : SaveUserPort {
    override fun save(user: User): User {
        TODO()
    }
}