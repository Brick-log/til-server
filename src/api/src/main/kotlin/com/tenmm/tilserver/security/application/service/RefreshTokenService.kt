package com.tenmm.tilserver.security.application.service

import com.tenmm.tilserver.common.domain.UnAuthorizedException
import com.tenmm.tilserver.security.application.inbound.GenerateTokenUseCase
import com.tenmm.tilserver.security.application.inbound.RefreshTokenUseCase
import com.tenmm.tilserver.security.application.outbound.GetRefreshTokenPort
import com.tenmm.tilserver.security.domain.SecurityToken
import com.tenmm.tilserver.security.domain.UserAuthInfo
import org.springframework.stereotype.Service

@Service
class RefreshTokenService(
    private val getRefreshTokenPort: GetRefreshTokenPort,
    private val generateTokenUseCase: GenerateTokenUseCase,
) : RefreshTokenUseCase {
    override suspend fun refresh(userAuthInfo: UserAuthInfo, securityToken: SecurityToken): SecurityToken {
        if (
            !getRefreshTokenPort.checkValidAccessToken(
                userIdentifier = userAuthInfo.userIdentifier,
                accessToken = securityToken.accessToken,
                refreshToken = securityToken.refreshToken
            )
        ) {
            throw UnAuthorizedException("Cannot found valid refresh token")
        }

        return generateTokenUseCase.generate(userIdentifier = userAuthInfo.userIdentifier)
    }
}
