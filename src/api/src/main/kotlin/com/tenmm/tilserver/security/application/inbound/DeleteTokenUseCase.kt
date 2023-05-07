package com.tenmm.tilserver.security.application.inbound

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.common.domain.OperationResult

interface DeleteTokenUseCase {
    suspend fun delete(userIdentifier: Identifier, accessToken: String): OperationResult
}
