package com.tenmm.tilserver.retrospect.application.inbound

import com.tenmm.tilserver.retrospect.adapter.inbound.Model.GetUserRetrospectResponseModel
import com.tenmm.tilserver.common.domain.Identifier

interface GetCategoryRetrospectUseCase {
    fun getRetrospectListByCategoryIdentifierWithPageToken(pageToken: String, categoryIdentifier: String, size: Int, userIdentifier: Identifier?): GetUserRetrospectResponseModel
    fun getRetrospectListByCategoryIdentifier(categoryIdentifier: String, size: Int, userIdentifier: Identifier?): GetUserRetrospectResponseModel

    fun getRecommendedRetrospectListRandom(): GetUserRetrospectResponseModel
    fun getRecommendedRetrospectListByCategory(categoryIdentifier: String): GetUserRetrospectResponseModel
}
