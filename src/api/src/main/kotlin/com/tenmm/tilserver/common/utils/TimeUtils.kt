package com.tenmm.tilserver.common.utils

import java.sql.Timestamp
import java.time.Clock
import java.time.Instant
import java.time.ZoneId

fun getNowTimestamp(): Timestamp {
    return Timestamp.from(Instant.now(Clock.system(ZoneId.of("Asia/Seoul"))))
}
