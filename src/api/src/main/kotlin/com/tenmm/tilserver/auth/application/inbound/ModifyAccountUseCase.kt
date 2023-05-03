package com.tenmm.tilserver.auth.application.inbound

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.common.domain.OperationResult

interface ModifyAccountUseCase {
    fun modifyMailAgreement(userIdentifier: Identifier, isOn: Boolean): OperationResult
}
