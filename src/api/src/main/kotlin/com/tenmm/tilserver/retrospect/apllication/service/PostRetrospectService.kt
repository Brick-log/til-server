package com.tenmm.tilserver.retrospect.application.service

import com.tenmm.tilserver.retrospect.application.inbound.PostRetrospectUseCase
import com.tenmm.tilserver.retrospect.application.outbound.PostRetrospectPort
import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.retrospect.adapter.inbound.Model.PostRetrospectRequestModel

import org.springframework.stereotype.Service

@Service
class PostRetrospectService(
    private val postRetrospectPort: PostRetrospectPort
) : PostRetrospectUseCase {
    override fun postRetrospect(userIdentifier: Identifier, postRetrospectRequestModel: PostRetrospectRequestModel) {
        postRetrospectPort.save(userIdentifier, postRetrospectRequestModel)
    }
}
