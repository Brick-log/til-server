package com.tenmm.tilserver.user.application.outbound

import com.tenmm.tilserver.user.domain.User

interface SaveUserPort {
    fun save(user: User): User
}
