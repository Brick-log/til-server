package com.tenmm.tilserver.security.application.inbound

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.security.domain.SecurityTokenType

interface ResolveTokenUseCase {
    fun resolveToken(token: String, securityTokenType: SecurityTokenType): Identifier
}
