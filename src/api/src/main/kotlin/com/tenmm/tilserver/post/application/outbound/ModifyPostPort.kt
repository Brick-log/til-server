package com.tenmm.tilserver.post.application.outbound

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.post.application.inbound.model.ModifyPostCommand

interface ModifyPostPort {
    fun modifyByIdentifier(command: ModifyPostCommand): Boolean
    fun increasePostHitCount(postIdentifier: Identifier)
}
