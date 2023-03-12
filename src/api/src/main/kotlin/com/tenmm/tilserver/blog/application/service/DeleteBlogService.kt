package com.tenmm.tilserver.blog.application.service

import com.tenmm.tilserver.blog.application.inbound.DeleteBlogUseCase
import com.tenmm.tilserver.blog.application.inbound.model.DeleteBlogCommand
import com.tenmm.tilserver.blog.application.outbound.DeleteBlogPort
import com.tenmm.tilserver.blog.application.outbound.GetBlogPort
import com.tenmm.tilserver.common.domain.OperationResult
import com.tenmm.tilserver.common.domain.UnAuthorizedException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DeleteBlogService(
    private val getBlogPort: GetBlogPort,
    private val deleteBlogPort: DeleteBlogPort
) : DeleteBlogUseCase {
    @Transactional
    override fun delete(command: DeleteBlogCommand): OperationResult {
        val blog = getBlogPort.getByBlogIdentifier(command.blogIdentifier)
        if (blog.userIdentifier != command.userIdentifier) {
            throw UnAuthorizedException()
        }

        return OperationResult(deleteBlogPort.deleteByIdentifier(command.blogIdentifier))
    }
}
