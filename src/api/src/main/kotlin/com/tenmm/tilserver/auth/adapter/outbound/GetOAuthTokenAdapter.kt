package com.tenmm.tilserver.auth.adapter.outbound

import com.tenmm.tilserver.auth.adapter.outbound.client.OAuthClientMap
import com.tenmm.tilserver.auth.application.outbound.GetOAuthTokenPort
import com.tenmm.tilserver.auth.application.outbound.model.OAuthTokenResult
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
