package com.tenmm.tilserver.blog.application.inbound

import com.tenmm.tilserver.blog.application.inbound.model.SaveBlogCommand
import com.tenmm.tilserver.common.domain.OperationResult

interface SaveBlogUseCase {
    fun save(command: SaveBlogCommand): OperationResult
}
