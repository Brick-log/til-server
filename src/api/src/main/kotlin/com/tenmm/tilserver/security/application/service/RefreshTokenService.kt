package com.tenmm.tilserver.security.application.service

import com.tenmm.tilserver.common.domain.UnAuthorizedException
import com.tenmm.tilserver.security.application.inbound.GenerateTokenUseCase
import com.tenmm.tilserver.security.application.inbound.RefreshTokenUseCase
import com.tenmm.tilserver.security.application.outbound.DeleteRefreshTokenPort
import com.tenmm.tilserver.security.application.outbound.GetRefreshTokenPort
import com.tenmm.tilserver.security.domain.SecurityToken
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class RefreshTokenService(
    private val getRefreshTokenPort: GetRefreshTokenPort,
    private val deleteRefreshTokenPort: DeleteRefreshTokenPort,
    private val generateTokenUseCase: GenerateTokenUseCase,
) : RefreshTokenUseCase {
    @Transactional
    override suspend fun refresh(securityToken: SecurityToken): SecurityToken {
        val (userIdentifier, accessToken, refreshToken) = securityToken
        getRefreshTokenPort.getAccessToken(userIdentifier, accessToken, refreshToken)
            ?: throw UnAuthorizedException()

        deleteRefreshTokenPort.delete(userIdentifier, accessToken)
        return generateTokenUseCase.generate(userIdentifier)
    }
}
