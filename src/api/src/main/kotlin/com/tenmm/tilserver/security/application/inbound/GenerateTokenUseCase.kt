package com.tenmm.tilserver.security.application.inbound

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.security.domain.SecurityToken

interface GenerateTokenUseCase {
    suspend fun generate(userIdentifier: Identifier): SecurityToken
}
