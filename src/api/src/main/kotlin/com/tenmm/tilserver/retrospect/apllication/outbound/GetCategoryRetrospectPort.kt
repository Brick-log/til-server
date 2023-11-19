package com.tenmm.tilserver.retrospect.application.outbound

import com.tenmm.tilserver.retrospect.adapter.outbound.model.RetrospectList
import com.tenmm.tilserver.common.domain.Identifier

interface GetCategoryRetrospectPort {
    fun getRetrospectListByTypeWithPageToken(pageToken: String, retrospectType: String, size: Int): RetrospectList
    fun getRetrospectListByType(retrospectType: String, size: Int): RetrospectList

    fun getRetrospectListByIdentifiers(retrospectIdentifiers: List<Identifier>): RetrospectList
    fun getByRetrospectType(retrospectType: String): List<Identifier>
    fun getRandom(size: Int): List<Identifier>
}
