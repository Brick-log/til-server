package com.tenmm.tilserver.retrospect.application.inbound

import com.tenmm.tilserver.common.domain.Identifier

interface PostCategoryRetrospectUseCase {
    fun addByRetrospectId(retrospectIdentifier: Identifier, retrospectType: String): Boolean
}
