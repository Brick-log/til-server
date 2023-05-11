package com.tenmm.tilserver.post.application.outbound

import com.tenmm.tilserver.common.domain.Identifier

interface GetRecommendedPostPort {
    fun getByCategoryIdentifier(categoryIdentifier: Identifier): List<Identifier>
    fun getRandom(size: Int): List<Identifier>
}
