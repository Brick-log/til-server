package com.tenmm.tilserver.account.application.inbound

import com.tenmm.tilserver.account.application.inbound.model.LogInResult
import org.hibernate.boot.model.naming.Identifier

interface LogOutUseCase {
    fun logOut(userIdentifier: Identifier): LogInResult
}
