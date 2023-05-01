package com.tenmm.tilserver.auth.application.service

import com.tenmm.tilserver.auth.application.inbound.OAuthLogInUseCase
import com.tenmm.tilserver.auth.application.inbound.model.CreateAccountCommand
import com.tenmm.tilserver.auth.application.inbound.model.LogInResult
import com.tenmm.tilserver.auth.application.jwt.GenerateTokenPort
import com.tenmm.tilserver.auth.application.outbound.oauth.GetOAuthTokenPort
import com.tenmm.tilserver.auth.application.outbound.oauth.GetOAuthUserInfoPort
import com.tenmm.tilserver.auth.application.outbound.persistence.GetAccountPort
import com.tenmm.tilserver.auth.application.outbound.persistence.SaveAccountPort
import com.tenmm.tilserver.auth.domain.OAuthType
import com.tenmm.tilserver.auth.domain.TokenType
import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.user.application.inbound.CreateUserUseCase
import com.tenmm.tilserver.user.application.inbound.model.CreateUserCommand
import java.util.UUID
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OAuthLogInService(
    private val createUserUseCase: CreateUserUseCase,
    private val getAccountPort: GetAccountPort,
    private val saveAccountPort: SaveAccountPort,
    private val getOAuthTokenPort: GetOAuthTokenPort,
    private val getOAuthUserInfoPort: GetOAuthUserInfoPort,
    private val generateTokenPort: GenerateTokenPort
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

        val account = getAccountPort.getByEmail(userInfo.email) ?: run {
            val identifier = Identifier.generate()
            val initialName = UUID.randomUUID().toString()

            val createUserCommand = CreateUserCommand(
                name = initialName,
                userIdentifier = identifier
            )
            val createAccountCommand = CreateAccountCommand(
                email = userInfo.email,
                type = type,
                userIdentifier = identifier
            )

            val account = saveAccountPort.save(createAccountCommand)
            val createUserResult = createUserUseCase.create(createUserCommand)

            if (account == null || !createUserResult.isSuccess) {
                throw IllegalArgumentException("SignUp Fail")
            }
            account
        }

        return LogInResult(
            accessToken = generateTokenPort.generateToken(
                userIdentifier = account.userIdentifier.value,
                tokenType = TokenType.ACCESS
            ),
            refreshToken = generateTokenPort.generateToken(
                userIdentifier = account.userIdentifier.value,
                tokenType = TokenType.REFRESH
            )
        )
    }
}
