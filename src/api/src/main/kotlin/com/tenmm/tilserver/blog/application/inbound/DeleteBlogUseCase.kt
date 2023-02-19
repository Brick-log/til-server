package com.tenmm.tilserver.blog.application.inbound

import com.tenmm.tilserver.blog.application.inbound.model.DeleteBlogCommand
import com.tenmm.tilserver.common.domain.OperationResult

interface DeleteBlogUseCase {
    fun delete(deleteBlogCommand: DeleteBlogCommand): OperationResult
}
