package com.tenmm.tilserver.account.application.service

import com.tenmm.tilserver.account.application.inbound.GetOAuthUserInfoUseCase
import com.tenmm.tilserver.account.application.inbound.LogInUseCase
import com.tenmm.tilserver.account.application.inbound.model.CreateAccountCommand
import com.tenmm.tilserver.account.application.inbound.model.LogInCommand
import com.tenmm.tilserver.account.application.inbound.model.LogInResult
import com.tenmm.tilserver.account.application.outbound.GetAccountPort
import com.tenmm.tilserver.account.application.outbound.OAuthUserInfo
import com.tenmm.tilserver.account.application.outbound.SaveAccountPort
import com.tenmm.tilserver.account.domain.Account
import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.security.application.inbound.GenerateTokenUseCase
import com.tenmm.tilserver.user.application.inbound.CreateUserUseCase
import com.tenmm.tilserver.user.application.inbound.model.CreateUserCommand
import org.apache.commons.lang3.RandomStringUtils
import org.springframework.stereotype.Service

@Service
class LogInService(
    private val getGoogleOAuthUserInfoService: GetOAuthUserInfoUseCase,
    private val createUserUseCase: CreateUserUseCase,
    private val getAccountPort: GetAccountPort,
    private val saveAccountPort: SaveAccountPort,
    private val generateTokenUseCase: GenerateTokenUseCase,
) : LogInUseCase {
    override suspend fun logIn(command: LogInCommand): LogInResult {
        val userInfo = getGoogleOAuthUserInfoService.getByOAuthToken(command.authorizeCode)

        val account = getAccountPort.getByEmail(userInfo.email) ?: register(userInfo)

        val securityToken = generateTokenUseCase.generate(userIdentifier = account.userIdentifier)
        return LogInResult(
            accessToken = securityToken.accessToken,
            refreshToken = securityToken.refreshToken
        )
    }

    private fun register(userInfo: OAuthUserInfo): Account {
        val identifier = Identifier.generate()
        val initialName = RandomStringUtils.random(10)

        val createUserCommand = CreateUserCommand(
            name = initialName,
            userIdentifier = identifier
        )
        val createAccountCommand = CreateAccountCommand(
            email = userInfo.email,
            type = userInfo.type,
            userIdentifier = identifier
        )

        val account = saveAccountPort.save(createAccountCommand)
        val createUserResult = createUserUseCase.create(createUserCommand)

        if (account == null || !createUserResult.isSuccess) {
            throw IllegalArgumentException("SignUp Fail")
        }
        return account
    }
}
