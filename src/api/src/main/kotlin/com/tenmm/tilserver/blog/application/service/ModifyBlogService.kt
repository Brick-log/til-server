package com.tenmm.tilserver.blog.application.service

import com.tenmm.tilserver.blog.application.inbound.ModifyBlogUseCase
import com.tenmm.tilserver.blog.application.inbound.model.ModifyBlogCommand
import com.tenmm.tilserver.blog.application.outbound.DeleteBlogPort
import com.tenmm.tilserver.blog.application.outbound.SaveBlogPort
import com.tenmm.tilserver.blog.domain.Blog
import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.common.domain.ModifyFailException
import com.tenmm.tilserver.common.domain.ModifyFailType
import com.tenmm.tilserver.common.domain.OperationResult
import mu.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

private val logger = KotlinLogging.logger {}

@Service
class ModifyBlogService(
    private val saveBlogPort: SaveBlogPort,
    private val deleteBlogPort: DeleteBlogPort,
) : ModifyBlogUseCase {
    @Transactional
    override fun modify(command: ModifyBlogCommand): OperationResult {
        try {
            deleteBlogPort.deleteAllByUserIdentifier(command.userIdentifier)
            val blogs = command.urls.map {
                Blog(
                    identifier = Identifier.generate(),
                    url = it,
                    userIdentifier = command.userIdentifier
                )
            }
            return OperationResult(saveBlogPort.saveAll(blogs))
        } catch (e: Exception) {
            logger.error(e) { "Modify blog Fail : $command" }
            throw ModifyFailException(ModifyFailType.BLOG)
        }
    }
}
