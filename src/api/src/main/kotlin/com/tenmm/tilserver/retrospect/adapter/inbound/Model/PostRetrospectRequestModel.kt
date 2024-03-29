package com.tenmm.tilserver.retrospect.adapter.inbound.Model

data class PostRetrospectRequestModel(
    val isSecret: Boolean,
    val questionType: String,
    val retrospect: List<SimpleRetrospect>
)
