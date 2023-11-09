package com.tenmm.tilserver.retrospect.application.inbound

import com.tenmm.tilserver.retrospect.adapter.inbound.Model.GetRetrospectResponseModel
import com.tenmm.tilserver.common.domain.Identifier

interface GetRetrospectUseCase {
    fun getRetrospect(userIdentifier: Identifier): List<GetRetrospectResponseModel>
}
