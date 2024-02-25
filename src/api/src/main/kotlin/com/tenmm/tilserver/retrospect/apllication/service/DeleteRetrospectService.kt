package com.tenmm.tilserver.retrospect.application.service

import com.tenmm.tilserver.retrospect.application.inbound.DeleteRetrospectUseCase
import com.tenmm.tilserver.retrospect.application.outbound.DeleteRetrospectPort

import com.tenmm.tilserver.common.domain.Identifier
import org.springframework.stereotype.Service

@Service
class DeleteRetrospectService(
    private val deleteRetrospectPort: DeleteRetrospectPort
) : DeleteRetrospectUseCase {
    override fun deleteRetrospect(userIdentifier: Identifier, retrospectIdentifier: Identifier) {
        deleteRetrospectPort.delete(userIdentifier, retrospectIdentifier)
    }
}
