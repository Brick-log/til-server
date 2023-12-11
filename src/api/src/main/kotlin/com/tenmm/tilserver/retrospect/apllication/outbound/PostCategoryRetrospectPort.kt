package com.tenmm.tilserver.retrospect.application.outbound

import com.tenmm.tilserver.common.domain.Identifier

interface PostCategoryRetrospectPort {
    fun addByRetrospectIdentifier(categoryIdentifier: Identifier, retrospectIdentifier: Identifier): Boolean
}
