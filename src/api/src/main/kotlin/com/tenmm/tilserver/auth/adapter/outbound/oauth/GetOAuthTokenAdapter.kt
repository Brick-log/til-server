package com.tenmm.tilserver.auth.adapter.outbound.oauth

import com.tenmm.tilserver.auth.adapter.outbound.oauth.provider.OAuthClientMap
import com.tenmm.tilserver.auth.application.outbound.oauth.GetOAuthTokenPort
import com.tenmm.tilserver.auth.application.outbound.oauth.model.OAuthTokenResult
import com.tenmm.tilserver.auth.domain.OAuthType
import org.springframework.stereotype.Component

@Component
class GetOAuthTokenAdapter(
    private val clientMap: OAuthClientMap,
) : GetOAuthTokenPort {
    override fun getOAuthTokens(
        authorizeCode: String,
        type: OAuthType
    ): OAuthTokenResult {
        val client = clientMap.findByType(type)
        return client.getToken(authorizeCode)
    }
}
