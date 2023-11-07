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
    override fun getRetrospect(userIdentifier: Identifier): GetRetrospectResponseModel {
        val retrospect = getRetrospectPort.findOneRetrospectByUserIdentifierToday(userIdentifier)
        if (retrospect == null) return GetRetrospectResponseModel(
            type = "NONE",
            retrospectIdentifier = "",
            retrospect = listOf()
        )
        val retrospectQnaList = getRetrospectPort.findRetrospectQnaListByRetrospectIdentifier(Identifier(retrospect.retrospectIdentifier))
        val simpleRetrospect = retrospectQnaList.map {
            SimpleRetrospect(
                question = it.question,
                answer = it.answer,
            )
        }
        return GetRetrospectResponseModel(
            type = retrospect.type,
            retrospectIdentifier = retrospect.retrospectIdentifier,
            retrospect = simpleRetrospect
        )
    }
}
