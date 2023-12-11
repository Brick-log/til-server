package com.tenmm.tilserver.retrospect.adapter.inbound.Model

data class GetRetrospectResponseModel(
    val questionType: String,
    val questionTypeName: String,
    val retrospectIdentifier: String,
    val retrospect: List<SimpleRetrospect>
)
