package com.tenmm.tilserver.post.adapter.inbound.rest.model

import java.sql.Timestamp

data class ConfirmUploadPostRequest(
    val identifier: String,
    val title: String,
    val summary: String,
    val createdAt: Timestamp,
)
