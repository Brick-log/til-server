package com.tenmm.tilserver.auth.adapter.outbound.persistence

import com.tenmm.tilserver.auth.adapter.outbound.persistence.converters.toModel
import com.tenmm.tilserver.auth.application.inbound.model.CreateAccountCommand
import com.tenmm.tilserver.auth.application.outbound.persistence.SaveAccountPort
import com.tenmm.tilserver.auth.domain.Account
import com.tenmm.tilserver.outbound.persistence.entity.AccountEntity
import com.tenmm.tilserver.outbound.persistence.entity.AccountStatus
import com.tenmm.tilserver.outbound.persistence.entity.OAuthType
import com.tenmm.tilserver.outbound.persistence.repository.AccountRepository
import mu.KotlinLogging
import org.springframework.stereotype.Component

private val logger = KotlinLogging.logger {}
@Component
class SaveAccountAdapter(
    private val accountRepository: AccountRepository
) : SaveAccountPort {

    override fun save(command: CreateAccountCommand): Account? {
        return try {
            val account = accountRepository.save(
                AccountEntity(
                    userIdentifier = command.userIdentifier.value,
                    oAuthType = OAuthType.valueOf(command.type.name),
                    email = command.email.value,
                    isSpamNotificationAgreed = false,
                    status = AccountStatus.ACTIVE
                )
            )
            account.toModel()
        } catch (e: Exception) {
            logger.error(e) { "Account save Fail - $command" }
            null
        }
    }
}
