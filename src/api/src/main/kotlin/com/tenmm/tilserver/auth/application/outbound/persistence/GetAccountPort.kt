package com.tenmm.tilserver.auth.application.outbound.persistence

import com.tenmm.tilserver.auth.domain.Account
import com.tenmm.tilserver.common.domain.Email

interface GetAccountPort {
    fun getByEmail(email: Email): Account?
}
