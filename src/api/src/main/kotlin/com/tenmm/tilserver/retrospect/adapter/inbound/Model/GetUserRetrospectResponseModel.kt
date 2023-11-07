package com.tenmm.tilserver.retrospect.adapter.inbound.Model

data class GetUserRetrospectResponseModel(
    val size: Int,
    val nextPageToken: String,
    val retrospects: List<DetailRetrospect>
)
