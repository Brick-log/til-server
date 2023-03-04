package com.tenmm.tilserver.post.application.outbound

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.post.application.outbound.model.ParsedPostResult

interface ParsedPostPort {
    fun getByIdentifier(identifier: Identifier): ParsedPostResult?
    fun deleteByIdentifier(identifier: Identifier): Boolean
}
