package com.tenmm.tilserver.auth.adapter.outbound.oauth.client.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

data class GoogleOAuthLoginResponse(
    @JsonProperty(value = "token_type")
    val tokenType: String,
    @JsonProperty(value = "access_token")
    val accessToken: String,
    @JsonProperty(value = "expires_in")
    val expiresIn: Long,
    @JsonProperty(value = "refresh_token")
    val refreshToken: String,
    @JsonProperty(value = "id_token")
    val idToken: String,
    @JsonProperty(value = "scope")
    val scope: String
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class GoogleOAuthUserInfoResponse(
    @JsonProperty("email")
    val email: String,
    @JsonProperty("name")
    val name: String,
    @JsonProperty("picture")
    val picture: String,
)
