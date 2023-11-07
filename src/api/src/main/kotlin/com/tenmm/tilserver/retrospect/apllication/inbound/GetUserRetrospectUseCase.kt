package com.tenmm.tilserver.retrospect.application.inbound
import com.tenmm.tilserver.retrospect.adapter.inbound.Model.GetUserRetrospectResponseModel
import com.tenmm.tilserver.retrospect.adapter.inbound.Model.GetRetrospectMetaResponseModel
import java.sql.Timestamp

interface GetUserRetrospectUseCase {
    fun getRetrospectListByNameAndDateWithPageToken(
        path: String,
        to: Timestamp,
        from: Timestamp,
        size: Int,
        pageToken: String?,
    ): GetUserRetrospectResponseModel

    fun getRetrospectMetaListByNameAndDate(
        path: String,
        to: Timestamp,
        from: Timestamp,
    ): GetRetrospectMetaResponseModel
}
