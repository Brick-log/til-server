package com.tenmm.tilserver.account.application.inbound

import com.tenmm.tilserver.common.domain.Identifier

interface LogOutUseCase {
    suspend fun logOut(userIdentifier: Identifier, accessToken: String)
}
