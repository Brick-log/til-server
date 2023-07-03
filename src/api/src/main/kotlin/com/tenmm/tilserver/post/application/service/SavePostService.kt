package com.tenmm.tilserver.post.application.service

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.common.domain.OperationResult
import com.tenmm.tilserver.common.domain.PostSaveFailException
import com.tenmm.tilserver.crawler.application.inbound.DoCrawlingUseCase
import com.tenmm.tilserver.post.application.inbound.GetPostUseCase
import com.tenmm.tilserver.post.application.inbound.SavePostUseCase
import com.tenmm.tilserver.post.application.inbound.model.PostSaveConfirmCommand
import com.tenmm.tilserver.post.application.inbound.model.PostSaveConfirmResult
import com.tenmm.tilserver.post.application.inbound.model.PostSaveRequestCommand
import com.tenmm.tilserver.post.application.inbound.model.PostSaveRequestResult
import com.tenmm.tilserver.post.application.outbound.SavePostPort
import com.tenmm.tilserver.post.domain.Post
import com.tenmm.tilserver.user.application.inbound.GetUserUseCase
import java.math.BigInteger
import org.springframework.stereotype.Service

@Service
class SavePostService(
    private val doCrawlingUseCase: DoCrawlingUseCase,
    private val savePostPort: SavePostPort,
    private val getPostUseCase: GetPostUseCase,
    private val getUserUseCase: GetUserUseCase,
) : SavePostUseCase {
    override suspend fun requestParse(command: PostSaveRequestCommand): PostSaveRequestResult {
        val parsedResult = doCrawlingUseCase.invoke(command.url, command.userIdentifier)
        return PostSaveRequestResult(
            url = command.url,
            title = parsedResult.title,
            description = parsedResult.description,
            createdAt = parsedResult.createdAt,
        )
    }

    override suspend fun savePost(command: PostSaveConfirmCommand): PostSaveConfirmResult {
        val userInfo = getUserUseCase.getByIdentifier(command.userIdentifier)
        val generatedPost = Post(
            identifier = Identifier.generate(),
            userIdentifier = command.userIdentifier,
            categoryIdentifier = userInfo.categoryIdentifier!!,
            title = command.title,
            description = command.description,
            url = command.url,
            createdAt = command.createdAt,
            hitCount = BigInteger.ZERO
        )

        val postYear = command.createdAt.toLocalDateTime().year
        val monthYear = command.createdAt.toLocalDateTime().monthValue

        return if (savePostPort.save(generatedPost)) {
            PostSaveConfirmResult(
                operationResult = OperationResult.success(),
                monthlyPublishCount = getPostUseCase.getPostCountByMonth(
                    userIdentifier = userInfo.identifier,
                    year = postYear,
                    month = monthYear
                ),
                month = monthYear,
                year = postYear,
            )
        } else {
            throw PostSaveFailException()
        }
    }
}
