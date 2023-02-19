package com.tenmm.tilserver.blog.application.service

import com.tenmm.tilserver.blog.application.inbound.ModifyBlogUseCase
import com.tenmm.tilserver.blog.application.inbound.model.ModifyBlogCommand
import com.tenmm.tilserver.common.domain.OperationResult
import org.springframework.stereotype.Service

@Service
class ModifyBlogService : ModifyBlogUseCase {
    override fun modify(command: ModifyBlogCommand): OperationResult {
        return OperationResult.success()
    }
}
