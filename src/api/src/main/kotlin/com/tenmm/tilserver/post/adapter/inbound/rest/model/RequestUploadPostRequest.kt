package com.tenmm.tilserver.post.adapter.inbound.rest.model

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.common.domain.Url

data class RequestUploadPostRequest(
    val userIdentifier: String,
    val url: String,
)
