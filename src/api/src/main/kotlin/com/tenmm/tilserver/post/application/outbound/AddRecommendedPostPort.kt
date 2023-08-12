package com.tenmm.tilserver.post.application.outbound

import com.tenmm.tilserver.common.domain.Identifier

interface AddRecommendedPostPort {
    fun addByPostIdentifier(
        categoryIdentifier: String,
        postIdentifier: Identifier,
    ): Boolean
}
