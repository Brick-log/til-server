package com.tenmm.tilserver.blog.application.service

import com.tenmm.tilserver.blog.application.inbound.DeleteBlogUseCase
import com.tenmm.tilserver.blog.application.inbound.model.DeleteBlogCommand
import com.tenmm.tilserver.common.domain.OperationResult
import org.springframework.stereotype.Service

@Service
class DeleteBlogService : DeleteBlogUseCase {
    override fun delete(deleteBlogCommand: DeleteBlogCommand): OperationResult {
        return OperationResult.success()
    }
}
