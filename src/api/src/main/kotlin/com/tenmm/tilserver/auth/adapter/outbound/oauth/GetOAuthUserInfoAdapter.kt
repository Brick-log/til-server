package com.tenmm.tilserver.auth.adapter.outbound.oauth

import com.tenmm.tilserver.auth.adapter.outbound.oauth.provider.OAuthClientMap
import com.tenmm.tilserver.auth.application.outbound.oauth.GetOAuthUserInfoPort
import com.tenmm.tilserver.auth.application.outbound.oauth.model.OAuthUserInfoResult
import com.tenmm.tilserver.auth.domain.OAuthType
import org.springframework.stereotype.Component

@Component
class GetOAuthUserInfoAdapter(
    private val clientMap: OAuthClientMap
) : GetOAuthUserInfoPort {
    override fun getUserInfo(
        accessToken: String,
        refreshToken: String,
        idToken: String?,
        type: OAuthType
    ): OAuthUserInfoResult {
        val client = clientMap.findByType(type)
        return client.getUserInfo(
            accessToken, refreshToken, idToken
        )
    }
}
