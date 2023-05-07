package com.tenmm.tilserver.account.application.inbound

import com.tenmm.tilserver.account.domain.Account
import com.tenmm.tilserver.common.domain.Identifier

interface GetAccountUseCase {
    fun getByUserIdentifier(userIdentifier: Identifier): Account
}
