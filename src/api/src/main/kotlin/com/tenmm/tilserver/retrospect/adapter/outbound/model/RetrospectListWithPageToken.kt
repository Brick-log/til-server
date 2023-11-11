package com.tenmm.tilserver.retrospect.adapter.outbound.model

import com.tenmm.tilserver.outbound.persistence.entity.RetrospectEntity

data class RetrospectListWithPageToken(
    val retrospectList: List<RetrospectEntity>,
    val nextPageToken: String?
)
