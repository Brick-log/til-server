package com.tenmm.tilserver.blog.application.inbound

import com.tenmm.tilserver.blog.application.inbound.model.ModifyBlogCommand
import com.tenmm.tilserver.common.domain.OperationResult

interface ModifyBlogUseCase {
    fun modify(command: ModifyBlogCommand): OperationResult
}
