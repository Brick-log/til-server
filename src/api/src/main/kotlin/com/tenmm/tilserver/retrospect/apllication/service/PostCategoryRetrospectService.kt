package com.tenmm.tilserver.retrospect.application.service

import com.tenmm.tilserver.retrospect.application.inbound.PostCategoryRetrospectUseCase
import com.tenmm.tilserver.retrospect.application.outbound.PostCategoryRetrospectPort

import org.springframework.stereotype.Service
import com.tenmm.tilserver.common.domain.Identifier

@Service
class PostCategoryRetrospectService(
    private val postCategoryRetrospectPort: PostCategoryRetrospectPort,
) : PostCategoryRetrospectUseCase {
    override fun addByRetrospectId(retrospectIdentifier: Identifier, categoryIdentifier: String): Boolean {
        return postCategoryRetrospectPort.addByRetrospectIdentifier(
            categoryIdentifier = categoryIdentifier,
            retrospectIdentifier = retrospectIdentifier
        )
    }
}
