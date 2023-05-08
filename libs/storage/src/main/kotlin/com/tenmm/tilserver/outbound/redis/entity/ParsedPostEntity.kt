package com.tenmm.tilserver.outbound.redis.entity

import java.sql.Timestamp

data class ParsedPostEntity(
    val identifier: String,
    val userIdentifier: String,
    val url: String,
    val title: String,
    val description: String?,
    val createdAt: Timestamp?,
) {
    companion object {
        fun generateKey(userIdentifier: String, identifier: String): String {
            return "$userIdentifier:$identifier"
        }
    }
}
