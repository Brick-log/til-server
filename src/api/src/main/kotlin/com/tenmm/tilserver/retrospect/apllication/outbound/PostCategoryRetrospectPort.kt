package com.tenmm.tilserver.retrospect.application.outbound

import com.tenmm.tilserver.common.domain.Identifier

interface PostCategoryRetrospectPort {
    fun addByRetrospectIdentifier(categoryIdentifier: String, retrospectIdentifier: Identifier): Boolean
}
