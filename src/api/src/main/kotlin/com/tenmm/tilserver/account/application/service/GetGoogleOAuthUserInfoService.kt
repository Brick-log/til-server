package com.tenmm.tilserver.account.application.service

import com.tenmm.tilserver.account.application.inbound.GetOAuthUserInfoUseCase
import com.tenmm.tilserver.account.application.outbound.GetOAuthTokenPort
import com.tenmm.tilserver.account.application.outbound.GetOAuthUserInfoPort
import com.tenmm.tilserver.account.application.outbound.OAuthUserInfo
import com.tenmm.tilserver.account.domain.OAuthType
import org.springframework.stereotype.Service

@Service
class GetGoogleOAuthUserInfoService(
    private val getOAuthTokenPort: GetOAuthTokenPort,
    private val getOAuthUserInfoPort: GetOAuthUserInfoPort,
) : GetOAuthUserInfoUseCase {
    override val oAuthType: OAuthType = OAuthType.GOOGLE

    override fun getByOAuthToken(oAuthToken: String): OAuthUserInfo {
        val tokens = getOAuthTokenPort.getOAuthTokens(oAuthToken, oAuthType)
        return getOAuthUserInfoPort.getUserInfo(
            accessToken = tokens.accessToken,
            refreshToken = tokens.refreshToken,
            idToken = tokens.idToken,
            type = oAuthType
        )
    }
}
