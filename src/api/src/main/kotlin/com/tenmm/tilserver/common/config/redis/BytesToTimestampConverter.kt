package com.tenmm.tilserver.common.config.redis

import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component
import java.sql.Timestamp
import java.nio.ByteBuffer

@Component
class BytesToTimestampConverter : Converter<ByteArray, Timestamp> {

    override fun convert(source: ByteArray): Timestamp {
        val buffer = ByteBuffer.wrap(source)
        val time = buffer.getLong()
        val nanos = buffer.getInt()
        return Timestamp(time).apply { this.nanos = nanos }
    }
}
