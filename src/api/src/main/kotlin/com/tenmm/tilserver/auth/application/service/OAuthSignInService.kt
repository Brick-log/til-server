package com.tenmm.tilserver.auth.application.service

import com.tenmm.tilserver.auth.application.inbound.OAuthSignInUseCase
import com.tenmm.tilserver.auth.application.inbound.model.SignInResult
import com.tenmm.tilserver.auth.application.outbound.GetAccountPort
import com.tenmm.tilserver.auth.application.outbound.GetOAuthTokenPort
import com.tenmm.tilserver.auth.application.outbound.GetOAuthUserInfoPort
import com.tenmm.tilserver.auth.domain.OAuthType
import com.tenmm.tilserver.post.application.outbound.SavePostPort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OAuthSignInService(
    private val getAccountPort: GetAccountPort,
    private val getOAuthTokenPort: GetOAuthTokenPort,
    private val getOAuthUserInfoPort: GetOAuthUserInfoPort,
) : OAuthSignInUseCase {
    @Transactional
    override fun signIn(authorizeCode: String, type: OAuthType): SignInResult? {
        val tokens = getOAuthTokenPort.getOAuthTokens(authorizeCode, type)
        val userInfo = getOAuthUserInfoPort.getUserInfo(
            accessToken = tokens.accessToken,
            refreshToken = tokens.refreshToken,
            idToken = tokens.idToken,
            type = OAuthType.GOOGLE
        )

        val account = getAccountPort.getByEmail(userInfo.email) ?: {
            /**
             * TODO
             * Account, User 계정 생성
             */
        }

        return null
    }
}
