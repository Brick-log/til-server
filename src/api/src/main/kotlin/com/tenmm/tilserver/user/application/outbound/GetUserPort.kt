package com.tenmm.tilserver.user.application.outbound

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.user.domain.User

interface GetUserPort {
    fun getByUserIdentifier(identifier: Identifier): User?
    fun getByName(name: String): User?
    fun getByPath(path: String): User?
}
