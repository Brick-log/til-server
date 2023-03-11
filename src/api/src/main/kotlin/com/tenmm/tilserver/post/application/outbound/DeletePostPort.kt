package com.tenmm.tilserver.post.application.outbound

import com.tenmm.tilserver.common.domain.Identifier

interface DeletePostPort {
    fun deleteByIdentifier(identifier: Identifier): Boolean
}
