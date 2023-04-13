package com.tenmm.tilserver.auth.application.service

import com.tenmm.tilserver.auth.application.inbound.OAuthLogInUseCase
import com.tenmm.tilserver.auth.application.inbound.model.LogInResult
import com.tenmm.tilserver.auth.application.outbound.GetAccountPort
import com.tenmm.tilserver.auth.application.outbound.GetOAuthTokenPort
import com.tenmm.tilserver.auth.application.outbound.GetOAuthUserInfoPort
import com.tenmm.tilserver.auth.domain.OAuthType
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OAuthLogInService(
    private val getAccountPort: GetAccountPort,
    private val getOAuthTokenPort: GetOAuthTokenPort,
    private val getOAuthUserInfoPort: GetOAuthUserInfoPort,
) : OAuthLogInUseCase {
    @Transactional
    override fun logIn(authorizeCode: String, type: OAuthType): LogInResult {
        val tokens = getOAuthTokenPort.getOAuthTokens(authorizeCode, type)
        val userInfo = getOAuthUserInfoPort.getUserInfo(
            accessToken = tokens.accessToken,
            refreshToken = tokens.refreshToken,
            idToken = tokens.idToken,
            type = type
        )

        val account = getAccountPort.getByEmail(userInfo.email) ?: {
            /**
             * TODO
             * Account, User 계정 생성
             */
        }

        return LogInResult(
            accessToken = "dfdf",
            refreshToken = "dfdf"
        )
    }
}
