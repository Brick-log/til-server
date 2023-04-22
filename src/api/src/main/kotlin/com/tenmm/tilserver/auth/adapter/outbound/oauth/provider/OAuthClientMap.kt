package com.tenmm.tilserver.auth.adapter.outbound.oauth.provider

import com.tenmm.tilserver.auth.domain.OAuthType
import org.springframework.stereotype.Component

@Component
class OAuthClientMap(
    googleOAuthProvider: GoogleOAuthProvider
) {

    private val clientMap: Map<OAuthType, OAuthProvider> = mapOf(
        OAuthType.GOOGLE to googleOAuthProvider
    )

    fun findByType(type: OAuthType): OAuthProvider {
        return clientMap[type]
            ?: throw IllegalArgumentException("OAuth Client Not Found ${type.name}")
    }
}
