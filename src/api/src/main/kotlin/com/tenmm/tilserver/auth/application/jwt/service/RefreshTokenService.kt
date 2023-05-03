package com.tenmm.tilserver.auth.application.jwt.service

import com.tenmm.tilserver.auth.application.jwt.GetRefreshTokenPort
import com.tenmm.tilserver.auth.application.jwt.inbound.GenerateTokenUseCase
import com.tenmm.tilserver.auth.application.jwt.inbound.RefreshTokenUseCase
import com.tenmm.tilserver.auth.domain.UserAuthInfo
import com.tenmm.tilserver.common.domain.UnAuthorizedException
import org.springframework.stereotype.Service

@Service
class RefreshTokenService(
    private val getRefreshTokenPort: GetRefreshTokenPort,
    private val generateTokenUseCase: GenerateTokenUseCase,
) : RefreshTokenUseCase {
    override fun refresh(userAuthInfo: UserAuthInfo, accessToken: String, refreshToken: String): String {
        if (
            !getRefreshTokenPort.checkValidAccessToken(
                userIdentifier = userAuthInfo.userIdentifier,
                accessToken = accessToken,
                refreshToken = refreshToken
            )
        ) {
            throw UnAuthorizedException("Cannot found valid refresh token")
        }

        return generateTokenUseCase.generate(userIdentifier = userAuthInfo.userIdentifier).accessToken
    }
}
