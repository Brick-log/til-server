package com.tenmm.tilserver.account.adapter.outbound.persistence

import com.tenmm.tilserver.account.adapter.outbound.persistence.converters.toModel
import com.tenmm.tilserver.account.application.outbound.GetAccountPort
import com.tenmm.tilserver.account.domain.Account
import com.tenmm.tilserver.common.domain.Email
import com.tenmm.tilserver.outbound.persistence.repository.AccountRepository
import org.springframework.stereotype.Component

@Component
class GetAccountAdapter(
    private val accountRepository: AccountRepository
) : GetAccountPort {
    override fun getByEmail(email: Email): Account? {
        return accountRepository.findByEmail(email.value)?.toModel()
    }
}