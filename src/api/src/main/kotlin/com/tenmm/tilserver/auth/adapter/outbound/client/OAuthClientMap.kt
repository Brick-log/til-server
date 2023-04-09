package com.tenmm.tilserver.auth.adapter.outbound.client

import com.tenmm.tilserver.auth.domain.OAuthType
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository
import org.springframework.stereotype.Component

@Component
class OAuthClientMap(
    private val clientRegistrationRepository: ClientRegistrationRepository
) {

    private val clientMap: Map<OAuthType, OAuthClient> = mapOf(OAuthType.GOOGLE to GoogleOAuthClient(clientRegistrationRepository.findByRegistrationId(OAuthType.GOOGLE.name.lowercase())))

    fun findByType(type: OAuthType): OAuthClient {
        return clientMap[type] ?: throw IllegalArgumentException("OAuth Client Not Found ${type.name}")
    }
}
