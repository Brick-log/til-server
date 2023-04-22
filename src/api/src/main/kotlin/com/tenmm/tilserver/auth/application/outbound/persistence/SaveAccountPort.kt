package com.tenmm.tilserver.auth.application.outbound.persistence

import com.tenmm.tilserver.auth.application.inbound.model.CreateAccountCommand
import com.tenmm.tilserver.auth.domain.Account

interface SaveAccountPort {
    fun save(command: CreateAccountCommand): Account?
}
