package com.tenmm.tilserver.retrospect.application.outbound

import com.tenmm.tilserver.retrospect.adapter.outbound.model.RetrospectList
import com.tenmm.tilserver.common.domain.Identifier

interface GetCategoryRetrospectPort {
    fun getRetrospectListByCategoryIdentifierWithPageToken(pageToken: String, categoryIdentifier: Identifier, size: Int): RetrospectList
    fun getRetrospectListByCategoryIdentifier(categoryIdentifier: Identifier, size: Int): RetrospectList

    fun getRetrospectListByIdentifiers(retrospectIdentifiers: List<Identifier>): RetrospectList
    fun getByCategoryIdentifier(categoryIdentifier: Identifier): List<Identifier>
    fun getRandom(size: Int): List<Identifier>
}
