package com.tenmm.tilserver.post.adapter.inbound.rest.model

import com.tenmm.tilserver.post.application.inbound.model.GetPostMetaResult

data class GetPostMetaResponse(
    val dateList: List<Long>,
) {
    companion object {
        fun fromResult(result: GetPostMetaResult): GetPostMetaResponse {
            return GetPostMetaResponse(
                dateList = result.dateList.map {
                    it.time
                }
            )
        }
    }
}
