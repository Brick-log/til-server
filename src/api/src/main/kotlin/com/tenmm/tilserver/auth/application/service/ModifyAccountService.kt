package com.tenmm.tilserver.auth.application.service

import com.tenmm.tilserver.auth.application.inbound.ModifyAccountUseCase
import com.tenmm.tilserver.auth.application.outbound.persistence.ModifyAccountPort
import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.common.domain.OperationResult
import org.springframework.stereotype.Service

@Service
class ModifyAccountService(
    private val modifyAccountPort: ModifyAccountPort,
) : ModifyAccountUseCase {
    override fun modifyMailAgreement(userIdentifier: Identifier, isOn: Boolean): OperationResult {
        return modifyAccountPort.modifyMailAgreement(userIdentifier, isOn)
    }
}
