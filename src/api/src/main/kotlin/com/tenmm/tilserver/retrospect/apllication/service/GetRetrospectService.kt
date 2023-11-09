package com.tenmm.tilserver.retrospect.application.service

import com.tenmm.tilserver.retrospect.application.inbound.GetRetrospectUseCase
import com.tenmm.tilserver.retrospect.application.outbound.GetRetrospectPort
import com.tenmm.tilserver.retrospect.adapter.inbound.Model.GetRetrospectResponseModel
import com.tenmm.tilserver.retrospect.adapter.inbound.Model.SimpleRetrospect
import com.tenmm.tilserver.common.domain.Identifier

import org.springframework.stereotype.Service

@Service
class GetRetrospectService(
    private val getRetrospectPort: GetRetrospectPort
) : GetRetrospectUseCase {
    override fun getRetrospect(userIdentifier: Identifier): List<GetRetrospectResponseModel> {
        return getRetrospectPort.findOneRetrospectByUserIdentifierToday(userIdentifier).map {
            GetRetrospectResponseModel(
                type = it.type,
                retrospectIdentifier = it.retrospectIdentifier,
                retrospect = getRetrospectPort.findRetrospectQnaListByRetrospectIdentifier(Identifier(it.retrospectIdentifier)).map {
                    SimpleRetrospect(
                        question = it.question,
                        answer = it.answer,
                    )
                }
            )
        }
    }
}
