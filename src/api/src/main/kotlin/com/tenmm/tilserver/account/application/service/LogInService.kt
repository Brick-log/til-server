package com.tenmm.tilserver.account.application.service

import com.tenmm.tilserver.account.application.inbound.LogInUseCase
import com.tenmm.tilserver.account.application.inbound.model.CreateAccountCommand
import com.tenmm.tilserver.account.application.inbound.model.LogInCommand
import com.tenmm.tilserver.account.application.inbound.model.LogInResult
import com.tenmm.tilserver.account.application.outbound.GetAccountPort
import com.tenmm.tilserver.account.application.outbound.GetOAuthTokenPort
import com.tenmm.tilserver.account.application.outbound.GetOAuthUserInfoPort
import com.tenmm.tilserver.account.application.outbound.SaveAccountPort
import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.security.application.inbound.GenerateTokenUseCase
import com.tenmm.tilserver.user.application.inbound.CreateUserUseCase
import com.tenmm.tilserver.user.application.inbound.model.CreateUserCommand
import java.util.UUID
import org.springframework.stereotype.Service

@Service
class LogInService(
    private val createUserUseCase: CreateUserUseCase,
    private val getAccountPort: GetAccountPort,
    private val saveAccountPort: SaveAccountPort,
    private val getOAuthTokenPort: GetOAuthTokenPort,
    private val getOAuthUserInfoPort: GetOAuthUserInfoPort,
    private val generateTokenUseCase: GenerateTokenUseCase,
) : LogInUseCase {
    override suspend fun logIn(command: LogInCommand): LogInResult {
        val tokens = getOAuthTokenPort.getOAuthTokens(command.authorizeCode, command.type)
        val userInfo = getOAuthUserInfoPort.getUserInfo(
            accessToken = tokens.accessToken,
            refreshToken = tokens.refreshToken,
            idToken = tokens.idToken,
            type = command.type
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
                type = command.type,
                userIdentifier = identifier
            )

            val account = saveAccountPort.save(createAccountCommand)
            val createUserResult = createUserUseCase.create(createUserCommand)

            if (account == null || !createUserResult.isSuccess) {
                throw IllegalArgumentException("SignUp Fail")
            }
            account
        }

        val securityToken = generateTokenUseCase.generate(userIdentifier = account.userIdentifier)
        return LogInResult(
            accessToken = securityToken.accessToken,
            refreshToken = securityToken.refreshToken
        )
    }
}
