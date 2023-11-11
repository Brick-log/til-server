package com.tenmm.tilserver.retrospect.application.outbound

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.retrospect.adapter.inbound.Model.PostRetrospectRequestModel

interface PostRetrospectPort {
    fun save(userIdentifier: Identifier, postRetrospectRequestModel: PostRetrospectRequestModel)
}
