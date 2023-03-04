package com.tenmm.tilserver.post.application.outbound

import com.tenmm.tilserver.post.application.inbound.model.ModifyPostCommand

interface ModifyPostPort {
    fun modifyByIdentifier(command: ModifyPostCommand): Boolean
}
