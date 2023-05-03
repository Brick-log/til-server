package com.tenmm.tilserver.auth.application.outbound.persistence

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.common.domain.OperationResult

interface ModifyAccountPort {
    fun modifyMailAgreement(userIdentifier: Identifier, isAgree: Boolean): OperationResult
}
