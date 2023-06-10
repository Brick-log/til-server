package com.tenmm.tilserver.post.application.service

import com.tenmm.tilserver.common.domain.CrawlingResultNotFoundException
import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.common.domain.OperationResult
import com.tenmm.tilserver.post.application.inbound.GetPostUseCase
import com.tenmm.tilserver.post.application.inbound.SavePostUseCase
import com.tenmm.tilserver.post.application.inbound.model.PostSaveConfirmCommand
import com.tenmm.tilserver.post.application.inbound.model.PostSaveConfirmResult
import com.tenmm.tilserver.post.application.inbound.model.PostSaveRequestCommand
import com.tenmm.tilserver.post.application.inbound.model.PostSaveRequestResult
import com.tenmm.tilserver.post.application.outbound.CrawlingPostPort
import com.tenmm.tilserver.post.application.outbound.DeleteParsedPostPort
import com.tenmm.tilserver.post.application.outbound.GetParsedPostPort
import com.tenmm.tilserver.post.application.outbound.SavePostPort
import com.tenmm.tilserver.post.domain.Post
import com.tenmm.tilserver.user.application.inbound.GetUserUseCase
import java.math.BigInteger
import java.time.LocalDate
import org.springframework.stereotype.Service

@Service
class SavePostService(
    private val crawlingPostPort: CrawlingPostPort,
    private val getParsedPostPort: GetParsedPostPort,
    private val deleteParsedPostPort: DeleteParsedPostPort,
    private val savePostPort: SavePostPort,
    private val getPostUseCase: GetPostUseCase,
    private val getUserUseCase: GetUserUseCase,
) : SavePostUseCase {
    override suspend fun requestSave(command: PostSaveRequestCommand): PostSaveRequestResult {
        val parsedPostIdentifier = crawlingPostPort.requestParseFromUrl(command.url, command.userIdentifier)
        val result = getParsedPostPort.findByIdentifier(command.userIdentifier, parsedPostIdentifier)
            ?: throw CrawlingResultNotFoundException()
        return PostSaveRequestResult(
            saveIdentifier = parsedPostIdentifier,
            url = command.url,
            title = result.title,
            description = result.description,
            createdAt = result.createdAt,
        )
    }

    override suspend fun confirmSave(command: PostSaveConfirmCommand): PostSaveConfirmResult {
        val parsedResult = getParsedPostPort.findByIdentifier(
            command.userIdentifier,
            command.saveIdentifier
        ) ?: throw CrawlingResultNotFoundException()
        val userInfo = getUserUseCase.getByIdentifier(parsedResult.userIdentifier)
        val generatedPost = Post(
            identifier = Identifier.generate(),
            userIdentifier = parsedResult.userIdentifier,
            categoryIdentifier = userInfo.categoryIdentifier!!,
            title = command.title,
            description = command.description,
            url = parsedResult.url,
            createdAt = command.createdAt,
            hitCount = BigInteger.ZERO
        )

        return if (savePostPort.save(generatedPost)) {
            PostSaveConfirmResult(
                operationResult = OperationResult.success(),
                monthlyPublishCount = getPostUseCase.getPostCountByMonth(
                    userIdentifier = userInfo.identifier,
                    year = LocalDate.now().year,
                    month = LocalDate.now().monthValue
                )
            )
        } else {
            PostSaveConfirmResult(
                operationResult = OperationResult.fail(),
                monthlyPublishCount = 0
            )
        }.apply {
            deleteParsedPostPort.deleteByIdentifier(command.userIdentifier, command.saveIdentifier)
        }
    }
}
