package com.tenmm.tilserver.retrospect.application.outbound

import com.tenmm.tilserver.common.domain.Identifier

interface PostCategoryRetrospectPort {
    fun addByRetrospectIdentifier(retrospectType: String, retrospectIdentifier: Identifier): Boolean
}
