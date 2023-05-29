package com.tenmm.tilserver.common.utils

import java.sql.Timestamp
import java.time.Clock
import java.time.Instant
import java.time.ZoneId

fun getTimeZone(): ZoneId = ZoneId.of("Asia/Seoul")
fun getNowInstant(): Instant = Instant.now(Clock.system(getTimeZone()))
fun getNowTimestamp(): Timestamp = Timestamp.from(getNowInstant())
