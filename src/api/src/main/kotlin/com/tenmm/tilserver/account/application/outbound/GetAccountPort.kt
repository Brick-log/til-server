package com.tenmm.tilserver.account.application.outbound

import com.tenmm.tilserver.account.domain.Account
import com.tenmm.tilserver.common.domain.Email
import com.tenmm.tilserver.common.domain.Identifier

interface GetAccountPort {
    fun getByEmail(email: Email): Account?
    fun getByUserIdentifier(userIdentifier: Identifier): Account?
}
