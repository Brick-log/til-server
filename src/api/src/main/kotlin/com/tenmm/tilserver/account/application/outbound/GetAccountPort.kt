package com.tenmm.tilserver.account.application.outbound

import com.tenmm.tilserver.account.domain.Account
import com.tenmm.tilserver.common.domain.Email

interface GetAccountPort {
    fun getByEmail(email: Email): Account?
}
