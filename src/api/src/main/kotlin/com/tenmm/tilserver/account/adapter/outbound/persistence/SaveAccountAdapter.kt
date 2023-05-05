package com.tenmm.tilserver.account.adapter.outbound.persistence

import com.tenmm.tilserver.account.adapter.outbound.persistence.converters.toModel
import com.tenmm.tilserver.account.application.model.CreateAccountCommand
import com.tenmm.tilserver.account.application.outbound.ModifyAccountPort
import com.tenmm.tilserver.account.application.outbound.SaveAccountPort
import com.tenmm.tilserver.account.domain.Account
import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.common.domain.NotFoundException
import com.tenmm.tilserver.common.domain.OperationResult
import com.tenmm.tilserver.outbound.persistence.entity.AccountEntity
import com.tenmm.tilserver.outbound.persistence.entity.AccountStatus
import com.tenmm.tilserver.outbound.persistence.entity.OAuthType
import com.tenmm.tilserver.outbound.persistence.repository.AccountRepository
import mu.KotlinLogging
import org.springframework.stereotype.Component

private val logger = KotlinLogging.logger {}

@Component
class SaveAccountAdapter(
    private val accountRepository: AccountRepository,
) : SaveAccountPort, ModifyAccountPort {

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

    override fun modifyMailAgreement(userIdentifier: Identifier, isAgree: Boolean): OperationResult {
        val account = accountRepository.findByUserIdentifier(userIdentifier = userIdentifier.value)
            ?: throw NotFoundException("ZZ")

        val newAccount = account.copy(
            isSpamNotificationAgreed = isAgree
        )

        accountRepository.save(newAccount)

        return OperationResult.success()
    }
}
