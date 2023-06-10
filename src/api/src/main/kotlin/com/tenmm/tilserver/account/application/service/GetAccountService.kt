package com.tenmm.tilserver.account.application.service

import com.tenmm.tilserver.account.application.inbound.GetAccountUseCase
import com.tenmm.tilserver.account.application.outbound.GetAccountPort
import com.tenmm.tilserver.account.domain.Account
import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.common.domain.UserNotFoundException
import org.springframework.stereotype.Service

@Service
class GetAccountService(
    private val getAccountPort: GetAccountPort,
) : GetAccountUseCase {
    override fun getByUserIdentifier(userIdentifier: Identifier): Account {
        return getAccountPort.getByUserIdentifier(userIdentifier) ?: throw UserNotFoundException()
    }
}
