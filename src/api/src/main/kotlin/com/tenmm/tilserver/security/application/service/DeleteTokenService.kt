package com.tenmm.tilserver.security.application.service

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.common.domain.OperationResult
import com.tenmm.tilserver.security.application.inbound.DeleteTokenUseCase
import com.tenmm.tilserver.security.application.outbound.DeleteRefreshTokenPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DeleteTokenService(
    private val deleteRefreshTokenPort: DeleteRefreshTokenPort,
) : DeleteTokenUseCase {
    @Transactional
    override suspend fun delete(userIdentifier: Identifier, accessToken: String): OperationResult {
        deleteRefreshTokenPort.delete(userIdentifier, accessToken)
        return OperationResult.success()
    }

    @Transactional
    override suspend fun deleteAllExpiredTokens() {
        deleteRefreshTokenPort.deleteExpiredTokens()
    }
}
