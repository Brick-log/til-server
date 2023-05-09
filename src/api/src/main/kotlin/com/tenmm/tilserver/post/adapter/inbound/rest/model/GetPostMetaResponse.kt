package com.tenmm.tilserver.post.adapter.inbound.rest.model

import com.tenmm.tilserver.post.application.inbound.model.GetPostMetaResult
import java.time.format.DateTimeFormatter

data class GetPostMetaResponse(
    val metas: List<String>,
) {
    companion object {
        fun fromResult(result: GetPostMetaResult): GetPostMetaResponse {
            return GetPostMetaResponse(
                metas = result.dateList.map {
                    it.format(postMetaTimeFormatter)
                }
            )
        }
    }
}

private val postMetaTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
