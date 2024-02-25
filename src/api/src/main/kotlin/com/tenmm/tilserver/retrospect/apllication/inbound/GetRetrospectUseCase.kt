package com.tenmm.tilserver.retrospect.application.inbound

import com.tenmm.tilserver.retrospect.adapter.inbound.Model.GetRetrospectResponseModel
import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.retrospect.application.inbound.Model.RetrospectDto

interface GetRetrospectUseCase {
    fun getRetrospect(userIdentifier: Identifier): List<GetRetrospectResponseModel>
    fun getRetrospectById(retrospectIdentifier: Identifier): RetrospectDto
}
