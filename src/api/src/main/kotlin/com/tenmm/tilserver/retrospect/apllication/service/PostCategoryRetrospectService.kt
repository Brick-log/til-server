package com.tenmm.tilserver.retrospect.application.service

import com.tenmm.tilserver.retrospect.application.inbound.PostCategoryRetrospectUseCase
import com.tenmm.tilserver.retrospect.application.outbound.PostCategoryRetrospectPort
import com.tenmm.tilserver.retrospect.application.inbound.GetRetrospectUseCase
import org.springframework.stereotype.Service
import com.tenmm.tilserver.common.domain.Identifier

@Service
class PostCategoryRetrospectService(
    private val postCategoryRetrospectPort: PostCategoryRetrospectPort,
    private val getRetrospectUseCase: GetRetrospectUseCase
) : PostCategoryRetrospectUseCase {
    override fun addByRetrospectId(retrospectIdentifier: Identifier): Boolean {
        val retrospect = getRetrospectUseCase.getRetrospectById(retrospectIdentifier)
        return postCategoryRetrospectPort.addByRetrospectIdentifier(
            retrospectIdentifier = retrospectIdentifier,
            categoryIdentifier = retrospect.categoryIdentifier ?: ""
        )
    }
}
