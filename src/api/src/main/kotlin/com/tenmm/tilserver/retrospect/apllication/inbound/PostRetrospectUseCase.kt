package com.tenmm.tilserver.retrospect.application.inbound
import com.tenmm.tilserver.common.domain.Identifier

import com.tenmm.tilserver.retrospect.adapter.inbound.Model.PostRetrospectRequestModel

interface PostRetrospectUseCase {
    fun postRetrospect(userIdentifier: Identifier, postRetrospectRequestModel: PostRetrospectRequestModel)
}
