package com.tenmm.tilserver.retrospect.application.outbound

import com.tenmm.tilserver.retrospect.adapter.outbound.model.RetrospectListWithPageToken
import com.tenmm.tilserver.retrospect.adapter.outbound.model.RetrospectList

interface GetCategoryRetrospectPort {
    fun getRetrospectListByTypeWithPageToken(pageToken: String, retrospectType: String, size: Int): RetrospectListWithPageToken
    fun getRetrospectListByType(retrospectType: String, size: Int): RetrospectList
}
