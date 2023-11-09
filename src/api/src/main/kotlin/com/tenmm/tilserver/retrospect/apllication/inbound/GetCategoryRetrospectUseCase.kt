package com.tenmm.tilserver.retrospect.application.inbound

import com.tenmm.tilserver.retrospect.adapter.inbound.Model.GetUserRetrospectResponseModel
import com.tenmm.tilserver.common.domain.Identifier

interface GetCategoryRetrospectUseCase {
    fun getRetrospectListByTypeWithPageToken(pageToken: String, retrospectType: String, size: Int, userIdentifier: Identifier?): GetUserRetrospectResponseModel
    fun getRetrospectListByType(retrospectType: String, size: Int, userIdentifier: Identifier?): GetUserRetrospectResponseModel
}
