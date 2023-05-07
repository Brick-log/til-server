package com.tenmm.tilserver.common.config.properties

import com.tenmm.tilserver.common.domain.Url
import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("profile-img")
data class ProfileImgProperties(
    val default: Url,
) {
    constructor(value: String) : this(Url(value))
}
