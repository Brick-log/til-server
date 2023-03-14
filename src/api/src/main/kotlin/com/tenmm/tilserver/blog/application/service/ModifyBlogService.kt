package com.tenmm.tilserver.blog.application.service

import com.tenmm.tilserver.blog.application.inbound.ModifyBlogUseCase
import com.tenmm.tilserver.blog.application.inbound.model.ModifyBlogCommand
import com.tenmm.tilserver.blog.application.outbound.GetBlogPort
import com.tenmm.tilserver.blog.application.outbound.ModifyBlogPort
import com.tenmm.tilserver.common.domain.OperationResult
import com.tenmm.tilserver.common.domain.UnAuthorizedException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ModifyBlogService(
    private val getBlogPort: GetBlogPort,
    private val modifyBlogPort: ModifyBlogPort
) : ModifyBlogUseCase {
    @Transactional
    override fun modify(command: ModifyBlogCommand): OperationResult {
        val blog = getBlogPort.getByBlogIdentifier(command.blogIdentifier)
        if (blog.userIdentifier != command.userIdentifier) {
            throw UnAuthorizedException()
        }
        return OperationResult(modifyBlogPort.modifyIdentifier(command))
    }
}
