package com.tenmm.tilserver.account.adapter.outbound.oauth

import com.tenmm.tilserver.account.adapter.outbound.oauth.provider.OAuthClientMap
import com.tenmm.tilserver.account.application.outbound.GetOAuthUserInfoPort
import com.tenmm.tilserver.account.application.outbound.OAuthUserInfoResult
import com.tenmm.tilserver.account.domain.OAuthType
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
