package com.tenmm.tilserver.auth.adapter.outbound

import com.tenmm.tilserver.auth.domain.OAuthType
import org.springframework.stereotype.Component

@Component
class OAuthClientMap {

    private lateinit var clientMap: Map<OAuthType, OAuthClient>
    init {
        clientMap = mapOf(OAuthType.GOOGLE to GoogleOAuthClient())
    }

    fun findByType(type: OAuthType): OAuthClient {
        return clientMap[type] ?: throw IllegalArgumentException("OAuth Client Not Found ${type.name}")
    }
}
