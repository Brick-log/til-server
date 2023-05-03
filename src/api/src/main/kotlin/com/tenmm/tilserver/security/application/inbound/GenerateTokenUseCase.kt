package com.tenmm.tilserver.security.application.inbound

import com.tenmm.tilserver.security.domain.SecurityToken
import com.tenmm.tilserver.common.domain.Identifier

interface GenerateTokenUseCase {
    fun generate(userIdentifier: Identifier): SecurityToken
}
