package com.tenmm.tilserver.account.application.service

import com.tenmm.tilserver.account.application.inbound.GetOAuthUserInfoUseCase
import com.tenmm.tilserver.account.application.inbound.LogInUseCase
import com.tenmm.tilserver.account.application.inbound.LogOutUseCase
import com.tenmm.tilserver.account.application.model.CreateAccountCommand
import com.tenmm.tilserver.account.application.model.LogInCommand
import com.tenmm.tilserver.account.application.model.LogInResult
import com.tenmm.tilserver.account.application.model.OAuthUserInfo
import com.tenmm.tilserver.account.application.outbound.GetAccountPort
import com.tenmm.tilserver.account.application.outbound.SaveAccountPort
import com.tenmm.tilserver.account.domain.Account
import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.common.domain.SignUpFailException
import com.tenmm.tilserver.security.application.inbound.DeleteTokenUseCase
import com.tenmm.tilserver.security.application.inbound.GenerateTokenUseCase
import com.tenmm.tilserver.user.application.inbound.CreateUserUseCase
import com.tenmm.tilserver.user.application.inbound.model.CreateUserCommand
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CertifyService(
    private val getGoogleOAuthUserInfoService: GetOAuthUserInfoUseCase,
    private val createUserUseCase: CreateUserUseCase,
    private val getAccountPort: GetAccountPort,
    private val saveAccountPort: SaveAccountPort,
    private val generateTokenUseCase: GenerateTokenUseCase,
    private val deleteTokenUseCase: DeleteTokenUseCase,
) : LogInUseCase, LogOutUseCase {

    @Transactional
    override suspend fun logIn(command: LogInCommand): LogInResult {
        val oAuthUserInfo = getGoogleOAuthUserInfoService.getByOAuthToken(command.authorizeCode, command.redirectUrl)

        val account = getAccountPort.getByEmail(oAuthUserInfo.email) ?: register(oAuthUserInfo)

        val securityToken = generateTokenUseCase.generate(userIdentifier = account.userIdentifier)
        return LogInResult(
            accessToken = securityToken.accessToken,
            refreshToken = securityToken.refreshToken
        )
    }

    private fun register(userInfo: OAuthUserInfo): Account {
        val userIdentifier = Identifier.generate()

        val createUserCommand = CreateUserCommand(
            name = userInfo.name,
            userIdentifier = userIdentifier
        )
        val createAccountCommand = CreateAccountCommand(
            email = userInfo.email,
            type = userInfo.type,
            userIdentifier = userIdentifier
        )

        val account = saveAccountPort.save(createAccountCommand)
        val createUserResult = createUserUseCase.create(createUserCommand)

        if (account == null || !createUserResult.isSuccess) {
            throw SignUpFailException()
        }
        return account
    }

    override suspend fun logOut(userIdentifier: Identifier, accessToken: String) {
        deleteTokenUseCase.delete(userIdentifier, accessToken)
    }
}
