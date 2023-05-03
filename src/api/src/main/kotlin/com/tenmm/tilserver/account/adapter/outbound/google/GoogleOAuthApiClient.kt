package com.tenmm.tilserver.account.adapter.outbound.google

import com.tenmm.tilserver.account.adapter.outbound.google.model.GoogleOAuthLoginResponse
import com.tenmm.tilserver.account.adapter.outbound.google.model.GoogleOAuthUserInfoResponse
import feign.Headers
import feign.Param
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "GoogleGetOAuthTokenApiClient", url = "\${spring.security.oauth2.client.provider.google.token-uri}")
interface GoogleGetOAuthTokenApi {
    @PostMapping
    @Headers(
        "Content-Type:application/x-www-form-urlencoded",
        "Accept-Charset:UTF-8"
    )
    fun getToken(
        @RequestParam("code") authorizeCode: String,
        @RequestParam("client_id") clientId: String,
        @RequestParam("client_secret") clientSecret: String,
        @RequestParam("redirect_uri") redirectUri: String,
        @RequestParam("grant_type") grantType: String
    ): GoogleOAuthLoginResponse
}

@FeignClient(name = "GoogleGetOAuthUserInfoApi", url = "\${spring.security.oauth2.client.provider.google.userinfo-uri}")
interface GoogleGetOAuthUserInfoApi {
    @PostMapping
    @Headers(
        "Content-Type:application/x-www-form-urlencoded",
        "Accept-Charset:UTF-8",
        "Authorization:Bearer{Authorization}",
    )
    fun getUserInfo(
        @Param("Authorization") authorization: String
    ): GoogleOAuthUserInfoResponse
}
