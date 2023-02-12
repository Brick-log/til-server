package com.tenmm.tilserver.common

import com.tenmm.tilserver.common.domain.Url
import org.apache.commons.lang3.RandomStringUtils

fun randomUrl(
    value: String = "https://${RandomStringUtils.randomAlphanumeric(1, 10)}.com",
): Url {
    return Url(value)
}
