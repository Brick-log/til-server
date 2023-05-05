package com.tenmm.tilserver.account.application.inbound

import com.tenmm.tilserver.account.application.model.LogInCommand
import com.tenmm.tilserver.account.application.model.LogInResult

interface LogInUseCase {
    suspend fun logIn(command: LogInCommand): LogInResult
}
