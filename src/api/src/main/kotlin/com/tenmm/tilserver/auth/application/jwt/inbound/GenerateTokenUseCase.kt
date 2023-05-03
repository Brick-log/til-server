package com.tenmm.tilserver.auth.application.jwt.inbound

import com.tenmm.tilserver.auth.domain.UserAuthToken
import com.tenmm.tilserver.common.domain.Identifier

interface GenerateTokenUseCase {
    fun generate(userIdentifier: Identifier): UserAuthToken
}
