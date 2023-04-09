package com.tenmm.tilserver.auth.application.service

import com.tenmm.tilserver.auth.application.inbound.OAuthSignInUseCase
import com.tenmm.tilserver.auth.application.inbound.model.SignInResult
import com.tenmm.tilserver.auth.application.outbound.GetOAuthTokenPort
import com.tenmm.tilserver.auth.application.outbound.GetOAuthUserInfoPort
import com.tenmm.tilserver.auth.domain.OAuthType
import org.springframework.stereotype.Service

@Service
class OAuthSignInService(
    private val getOAuthTokenPort: GetOAuthTokenPort,
    private val getOAuthUserInfoPort: GetOAuthUserInfoPort
) : OAuthSignInUseCase {
    override fun signIn(authorizeCode: String, type: OAuthType): SignInResult? {
        val tokens = getOAuthTokenPort.getOAuthTokens(authorizeCode, type)
        val userInfo = getOAuthUserInfoPort.getUserInfo(
            accessToken = tokens.accessToken,
            refreshToken = tokens.refreshToken,
            idToken = tokens.idToken,
            type = OAuthType.GOOGLE
        )
        return null
    }
}
