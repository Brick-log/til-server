package com.tenmm.tilserver.account.application.service

import com.tenmm.tilserver.account.application.inbound.ModifyAccountUseCase
import com.tenmm.tilserver.account.application.outbound.ModifyAccountPort
import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.common.domain.ModifyFailException
import com.tenmm.tilserver.common.domain.ModifyFailType
import com.tenmm.tilserver.common.domain.OperationResult
import mu.KotlinLogging
import org.springframework.stereotype.Service

private val logger = KotlinLogging.logger {}
@Service
class ModifyAccountService(
    private val modifyAccountPort: ModifyAccountPort,
) : ModifyAccountUseCase {
    override fun modifyMailAgreement(userIdentifier: Identifier, isOn: Boolean): OperationResult {
        try {
            return modifyAccountPort.modifyMailAgreement(userIdentifier, isOn)
        } catch (e: Exception) {
            logger.error(e) { "Modify mail_agreement Fail : $userIdentifier" }
            throw ModifyFailException(ModifyFailType.MAIL_AGREEMENT)
        }
    }
}
