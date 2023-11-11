package com.tenmm.tilserver.retrospect.adapter.inbound.Model

data class GetRetrospectResponseModel(
    val type: String,
    val retrospectIdentifier: String,
    val retrospect: List<SimpleRetrospect>
)
