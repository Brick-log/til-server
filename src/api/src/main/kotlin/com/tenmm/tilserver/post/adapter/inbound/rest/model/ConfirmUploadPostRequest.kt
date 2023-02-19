package com.tenmm.tilserver.post.adapter.inbound.rest.model

import com.tenmm.tilserver.common.domain.Identifier
import java.sql.Timestamp

data class ConfirmUploadPostRequest(
    val saveIdentifier: Identifier,
    val title: String,
    val description: String,
    val createdAt: Timestamp,
)
