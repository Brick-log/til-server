package com.tenmm.tilserver.common.security

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("security.aes128")
data class SecurityProperties(
    val key: String,
)
