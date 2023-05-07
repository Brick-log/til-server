package com.tenmm.tilserver.post.application.outbound

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.post.application.outbound.model.ParsedPostResult

interface ParsedPostPort {
    fun findById(identifier: Identifier): ParsedPostResult?
    fun deleteById(identifier: Identifier): Boolean
}
