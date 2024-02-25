package com.tenmm.tilserver.retrospect.adapter.outbound

import com.tenmm.tilserver.retrospect.application.outbound.DeleteRetrospectPort
import org.springframework.stereotype.Component

import com.tenmm.tilserver.outbound.persistence.repository.RetrospectRepository
import com.tenmm.tilserver.outbound.persistence.repository.RetrospectQnaRepository
import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.common.domain.RetrospectNotFoundException
import org.springframework.transaction.annotation.Transactional

@Component
class DeleteRetrospectAdapter(
    private val retrospectQnaRepository: RetrospectQnaRepository,
    private val retrospectRepository: RetrospectRepository
) : DeleteRetrospectPort {
    @Transactional
    override fun delete(userIdentifier: Identifier, retrospectIdentifier: Identifier) {
        if (retrospectRepository.findByUserIdentifierAndRetrospectIdentifier(userIdentifier.value, retrospectIdentifier.value) != null) {
            retrospectRepository.deleteByUserIdentifierAndRetrospectIdentifier(userIdentifier.value, retrospectIdentifier.value)
            retrospectQnaRepository.deleteByRetrospectIdentifier(retrospectIdentifier.value)
        } else {
            throw RetrospectNotFoundException()
        }
    }
}
