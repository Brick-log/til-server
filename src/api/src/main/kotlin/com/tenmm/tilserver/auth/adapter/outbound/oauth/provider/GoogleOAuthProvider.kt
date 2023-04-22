package com.tenmm.tilserver.auth.adapter.outbound.oauth.provider

import com.fasterxml.jackson.databind.ObjectMapper
import com.tenmm.tilserver.auth.adapter.outbound.oauth.provider.client.GoogleGetOAuthTokenApi
import com.tenmm.tilserver.auth.adapter.outbound.oauth.provider.client.GoogleGetOAuthUserInfoApi
import com.tenmm.tilserver.auth.adapter.outbound.oauth.provider.client.model.GoogleOAuthUserInfoResponse
import com.tenmm.tilserver.auth.application.outbound.oauth.model.OAuthTokenResult
import com.tenmm.tilserver.auth.application.outbound.oauth.model.OAuthUserInfoResult
import com.tenmm.tilserver.auth.domain.OAuthType
import com.tenmm.tilserver.common.domain.Email
import com.tenmm.tilserver.common.domain.Url
import java.util.Base64
import javax.annotation.PostConstruct
import org.springframework.security.oauth2.client.registration.ClientRegistration
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class GoogleOAuthProvider(
    private val getTokenApi: GoogleGetOAuthTokenApi,
    private val getUserInfoApi: GoogleGetOAuthUserInfoApi,
    private val clientRegistrationRepository: ReactiveClientRegistrationRepository
) : OAuthProvider {

    lateinit var registration: ClientRegistration

    @PostConstruct
    fun init() {
        registration = clientRegistrationRepository.findByRegistrationId(OAuthType.GOOGLE.name.lowercase())
            .switchIfEmpty(
                Mono.error(IllegalArgumentException("OAuth Client Not Found $"))
            )
            .block()!!
    }

    private val objectMapper = ObjectMapper()
    override fun getToken(authorizeCode: String): OAuthTokenResult {
        val response = getTokenApi.getToken(
            authorizeCode = authorizeCode,
            clientId = registration.clientId,
            clientSecret = registration.clientSecret,
            redirectUri = registration.redirectUri,
            grantType = registration.authorizationGrantType.value
        )

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
    ): OAuthUserInfoResult {
        if (idToken != null) {
            val base64EncodedPayload = idToken.split(".")[1]
            val base64DecodedPayload = Base64.getDecoder().decode(base64EncodedPayload)
            val payload = String(base64DecodedPayload, Charsets.UTF_8)
            val response = objectMapper.readValue(payload, GoogleOAuthUserInfoResponse::class.java)

            return OAuthUserInfoResult(
                name = response.name,
                email = Email(response.email),
                profileImgUrl = Url(response.picture)
            )
        } else {
            val response =
                getUserInfoApi.getUserInfo(accessToken)
            return OAuthUserInfoResult(
                name = response.name,
                email = Email(response.email),
                profileImgUrl = Url(response.picture)
            )
        }
    }

    override fun getProvider(): OAuthType {
        return OAuthType.GOOGLE
    }
}
