package com.tenmm.tilserver.alarm.domain

import com.tenmm.tilserver.common.constants.CRON_ITERATION_REGEX
import com.tenmm.tilserver.common.domain.Identifier

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
