package com.tenmm.tilserver.alaram.domain

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.common.utils.CRON_ITERATION_REGEX

data class Alarm(
    val userIdentifier: Identifier,
    val enable: Boolean,
    val iteration: String,
) {
    init {
        if (!CRON_ITERATION_REGEX.matches(iteration)) {
            throw IllegalArgumentException("Invalid iteration: $iteration")
        }
    }
}
