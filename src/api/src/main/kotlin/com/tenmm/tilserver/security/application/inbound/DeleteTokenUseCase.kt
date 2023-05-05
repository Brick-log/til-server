package com.tenmm.tilserver.security.application.inbound

import com.tenmm.tilserver.common.domain.Identifier

interface DeleteTokenUseCase {
    suspend fun delete(userIdentifier: Identifier, accessToken: String): Boolean
}
