package com.tenmm.tilserver.account.adapter.outbound.google

import com.fasterxml.jackson.databind.ObjectMapper
import com.tenmm.tilserver.account.adapter.outbound.google.model.GoogleOAuthUserInfoResponse
import com.tenmm.tilserver.account.application.model.OAuthTokenResult
import com.tenmm.tilserver.account.application.model.OAuthUserInfo
import com.tenmm.tilserver.account.application.outbound.GetOAuthTokenPort
import com.tenmm.tilserver.account.application.outbound.GetOAuthUserInfoPort
import com.tenmm.tilserver.account.domain.OAuthType
import com.tenmm.tilserver.common.domain.Email
import com.tenmm.tilserver.common.domain.OAuthFailException
import com.tenmm.tilserver.common.domain.Url
import java.util.Base64
import javax.annotation.PostConstruct
import mu.KotlinLogging
import org.apache.logging.log4j.util.Strings
import org.springframework.security.oauth2.client.registration.ClientRegistration
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

private val logger = KotlinLogging.logger {}

@Component
class GetGoogleOAuthAdapter(
    private val getTokenApi: GoogleGetOAuthTokenApi,
    private val getUserInfoApi: GoogleGetOAuthUserInfoApi,
    private val clientRegistrationRepository: ReactiveClientRegistrationRepository,
) : GetOAuthTokenPort, GetOAuthUserInfoPort {

    private val objectMapper = ObjectMapper()

    lateinit var registration: ClientRegistration

    @PostConstruct
    fun init() {
        registration = clientRegistrationRepository.findByRegistrationId(OAuthType.GOOGLE.name.lowercase())
            .switchIfEmpty(
                Mono.error(IllegalArgumentException("OAuth Client Not Found $"))
            )
            .block()!!
    }

    override fun getOAuthTokens(
        authorizeCode: String,
        type: OAuthType,
        redirectUrl: Url
    ): OAuthTokenResult {
        val response = try {
            getTokenApi.getToken(
                authorizeCode = authorizeCode,
                clientId = registration.clientId,
                clientSecret = registration.clientSecret,
                redirectUri = redirectUrl.value,
                grantType = registration.authorizationGrantType.value
            )
        } catch (e: Exception) {
            logger.error(e) { "OAuth to google fail" }
            throw OAuthFailException(OAuthType.GOOGLE, e.message ?: Strings.EMPTY)
        }

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
        type: OAuthType,
    ): OAuthUserInfo {
        if (idToken != null) {
            val base64EncodedPayload = idToken.split(".")[1]
            val base64DecodedPayload = Base64.getDecoder().decode(base64EncodedPayload)
            val payload = String(base64DecodedPayload, Charsets.UTF_8)
            val response = objectMapper.readValue(payload, GoogleOAuthUserInfoResponse::class.java)

            val name = response.givenName.substring(0, minOf(response.givenName.length, 10))

            return OAuthUserInfo(
                name = name,
                email = Email(response.email),
                profileImgUrl = Url(response.picture),
                type = type
            )
        } else {
            val response = getUserInfoApi.getUserInfo(accessToken)
            return OAuthUserInfo(
                name = response.givenName,
                email = Email(response.email),
                profileImgUrl = Url(response.picture),
                type = type
            )
        }
    }
}
