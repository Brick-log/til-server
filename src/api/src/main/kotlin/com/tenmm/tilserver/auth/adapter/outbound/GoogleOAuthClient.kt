package com.tenmm.tilserver.auth.adapter.outbound

import com.fasterxml.jackson.databind.ObjectMapper
import com.tenmm.tilserver.auth.adapter.outbound.model.GoogleOAuthLoginResponse
import com.tenmm.tilserver.auth.adapter.outbound.model.GoogleOAuthUserInfoResponse
import com.tenmm.tilserver.auth.application.outbound.model.OAuthTokenResult
import com.tenmm.tilserver.auth.application.outbound.model.OAuthUserInfoResult
import com.tenmm.tilserver.auth.domain.OAuthType
import java.nio.charset.StandardCharsets
import java.util.Base64
import java.util.Collections
import org.springframework.http.MediaType
import org.springframework.security.oauth2.client.registration.ClientRegistration
import org.springframework.web.reactive.function.client.WebClient

class GoogleOAuthClient : OAuthClient {

    private val objectMapper = ObjectMapper()
    override fun getToken(registration: ClientRegistration, authorizeCode: String): OAuthTokenResult {
        val response =
            WebClient
                .create()
                .post()
                .uri(registration.providerDetails.tokenUri) { uriBuilder ->
                    uriBuilder
                        .queryParam("code", authorizeCode)
                        .queryParam("client_id", registration.clientId)
                        .queryParam("client_secret", registration.clientSecret)
                        .queryParam("redirect_uri", registration.redirectUri)
                        .queryParam("grant_type", registration.authorizationGrantType.value)
                        .build()
                }
                .headers {
                    it.contentType = MediaType.APPLICATION_FORM_URLENCODED
                    it.acceptCharset = Collections.singletonList(StandardCharsets.UTF_8)
                }
                .retrieve()
                .bodyToMono(GoogleOAuthLoginResponse::class.java)
                .block() ?: throw IllegalArgumentException("OAuth Authorization Fail: Google")
        return OAuthTokenResult(
            accessToken = response.accessToken,
            refreshToken = response.refreshToken,
            idToken = response.idToken
        )
    }

    override fun getUserInfo(
        accessToken: String,
        refreshToken: String,
        idToken: String?,
        registration: ClientRegistration
    ): OAuthUserInfoResult {
        if (idToken != null) {

            val base64EncodedPayload = idToken.split(".")[1]
            val base64DecodedPayload = Base64.getDecoder().decode(base64EncodedPayload)
            val payload = String(base64DecodedPayload, Charsets.UTF_8)
            val response = objectMapper.readValue(payload, GoogleOAuthUserInfoResponse::class.java)

            return OAuthUserInfoResult(
                email = response.email
            )
        } else {
            val response =
                WebClient
                    .create()
                    .get()
                    .uri(registration.providerDetails.userInfoEndpoint.uri)
                    .headers {
                        it.contentType = MediaType.APPLICATION_FORM_URLENCODED
                        it.acceptCharset = Collections.singletonList(StandardCharsets.UTF_8)
                        it.setBearerAuth(accessToken)
                    }
                    .retrieve()
                    .bodyToMono(GoogleOAuthUserInfoResponse::class.java)
                    .block() ?: throw IllegalArgumentException("OAuth Get UserInfo Fail: Google")
            return OAuthUserInfoResult(
                email = response.email
            )
        }
    }

    override fun getProvider(): OAuthType {
        return OAuthType.GOOGLE
    }

    override fun getAuthorizationCodeRedirectURI(registration: ClientRegistration): String {
        return "${registration.providerDetails.authorizationUri}?access_type=offline&prompt=consent&client_id=${registration.clientId}&redirect_uri=${registration.redirectUri}&response_type=code&scope=${
        registration.scopes.joinToString(
            " "
        )
        }"
    }
}
