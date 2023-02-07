package com.tenmm.tilserver.post.application.inbound.model

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.common.domain.Url

data class PostSaveRequestCommand(
    val userIdentifier: Identifier,
    val url: Url,
)
