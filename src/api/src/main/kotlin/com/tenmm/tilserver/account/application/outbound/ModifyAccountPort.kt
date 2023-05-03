package com.tenmm.tilserver.account.application.outbound

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.common.domain.OperationResult

interface ModifyAccountPort {
    fun modifyMailAgreement(userIdentifier: Identifier, isAgree: Boolean): OperationResult
}
