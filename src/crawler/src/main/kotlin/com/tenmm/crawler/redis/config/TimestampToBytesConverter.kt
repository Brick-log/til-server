package com.tenmm.crawler.common.config.redis

import org.springframework.core.convert.converter.Converter
import java.nio.ByteBuffer
import java.sql.Timestamp
import org.springframework.stereotype.Component

@Component
class TimestampToBytesConverter : Converter<Timestamp, ByteArray> {

    override fun convert(timestamp: Timestamp): ByteArray {
        val buffer = ByteBuffer.allocate(java.lang.Long.BYTES + java.lang.Integer.BYTES)
        buffer.putLong(timestamp.time)
        buffer.putInt(timestamp.nanos)
        return buffer.array()
    }
}
