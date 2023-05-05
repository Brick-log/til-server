package com.tenmm.tilserver.account.application.outbound

import com.tenmm.tilserver.account.application.model.CreateAccountCommand
import com.tenmm.tilserver.account.domain.Account

interface SaveAccountPort {
    fun save(command: CreateAccountCommand): Account?
}
