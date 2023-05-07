package com.tenmm.tilserver.post.application.service

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.common.domain.NotFoundException
import com.tenmm.tilserver.common.domain.OperationResult
import com.tenmm.tilserver.post.application.inbound.GetPostUseCase
import com.tenmm.tilserver.post.application.inbound.SavePostUseCase
import com.tenmm.tilserver.post.application.inbound.model.PostSaveConfirmCommand
import com.tenmm.tilserver.post.application.inbound.model.PostSaveConfirmResult
import com.tenmm.tilserver.post.application.inbound.model.PostSaveRequestCommand
import com.tenmm.tilserver.post.application.inbound.model.PostSaveRequestResult
import com.tenmm.tilserver.post.application.outbound.CrawlingPostPort
import com.tenmm.tilserver.post.application.outbound.ParsedPostPort
import com.tenmm.tilserver.post.application.outbound.SavePostPort
import com.tenmm.tilserver.post.domain.Post
import com.tenmm.tilserver.user.application.inbound.GetUserUseCase
import java.math.BigInteger
import java.time.LocalDate
import org.springframework.stereotype.Service

@Service
class SavePostService(
    private val crawlingPostPort: CrawlingPostPort,
    private val parsedPostPort: ParsedPostPort,
    private val savePostPort: SavePostPort,
    private val getPostUseCase: GetPostUseCase,
    private val getUserUseCase: GetUserUseCase,
) : SavePostUseCase {
    override suspend fun requestSave(command: PostSaveRequestCommand): PostSaveRequestResult {
        val identifier = crawlingPostPort.requestParseFromUrl(command.url, command.userIdentifier)
        val result = parsedPostPort.findById(identifier)
            ?: throw NotFoundException("Not found parsing info - $identifier")
        return PostSaveRequestResult(
            saveIdentifier = identifier,
            url = command.url,
            title = result.title,
            description = result.description,
            createdAt = result.createdAt,
        )
    }

    override fun confirmSave(command: PostSaveConfirmCommand): PostSaveConfirmResult {
        val parsedResult = parsedPostPort.findById(command.saveIdentifier)
            ?: throw NotFoundException("Not found parsing info - ${command.saveIdentifier}")
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
            parsedPostPort.deleteByIdentifier(command.saveIdentifier)
        }
    }
}
