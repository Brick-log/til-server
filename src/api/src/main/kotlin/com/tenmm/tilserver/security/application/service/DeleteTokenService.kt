package com.tenmm.tilserver.security.application.service

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.common.domain.OperationResult
import com.tenmm.tilserver.security.application.inbound.DeleteTokenUseCase
import com.tenmm.tilserver.security.application.outbound.DeleteSecurityTokenPort
import org.springframework.stereotype.Service

@Service
class DeleteTokenService(
    private val deleteSecurityTokenPort: DeleteSecurityTokenPort,
) : DeleteTokenUseCase {
    override suspend fun delete(userIdentifier: Identifier, accessToken: String): OperationResult {
        deleteSecurityTokenPort.delete(userIdentifier, accessToken)
        return OperationResult.success()
    }
}
