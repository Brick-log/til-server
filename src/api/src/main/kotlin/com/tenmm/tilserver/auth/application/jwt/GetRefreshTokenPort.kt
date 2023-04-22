package com.tenmm.tilserver.auth.application.jwt

interface GetRefreshTokenPort {
    fun findByUserIdentifier(userIdentifier: String): String?
}
