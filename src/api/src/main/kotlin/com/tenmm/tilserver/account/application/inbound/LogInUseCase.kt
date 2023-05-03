package com.tenmm.tilserver.account.application.inbound

import com.tenmm.tilserver.account.application.inbound.model.LogInCommand
import com.tenmm.tilserver.account.application.inbound.model.LogInResult

interface LogInUseCase {
    suspend fun logIn(command: LogInCommand): LogInResult
}
