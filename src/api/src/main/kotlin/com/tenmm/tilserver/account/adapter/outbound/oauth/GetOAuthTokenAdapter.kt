package com.tenmm.tilserver.account.adapter.outbound.oauth

import com.tenmm.tilserver.account.adapter.outbound.oauth.provider.OAuthClientMap
import com.tenmm.tilserver.account.application.outbound.GetOAuthTokenPort
import com.tenmm.tilserver.account.application.outbound.OAuthTokenResult
import com.tenmm.tilserver.account.domain.OAuthType
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
