package com.tenmm.tilserver.retrospect.adapter.outbound

import com.tenmm.tilserver.retrospect.application.outbound.GetCategoryRetrospectPort
import com.tenmm.tilserver.retrospect.adapter.outbound.model.RetrospectList
import com.tenmm.tilserver.outbound.persistence.repository.RetrospectQnaRepository
import com.tenmm.tilserver.outbound.persistence.repository.RecommendRetrospectRepository
import com.tenmm.tilserver.outbound.persistence.repository.RetrospectRepository
import org.springframework.stereotype.Component
import java.sql.Timestamp
import org.springframework.data.domain.PageRequest

import com.tenmm.tilserver.outbound.persistence.entity.RetrospectEntity
import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.common.utils.CryptoHandler

@Component
class GetCategoryRetrospectAdapter(
    private val cryptoHandler: CryptoHandler,
    private val retrospectQnaRepository: RetrospectQnaRepository,
    private val retrospectRepository: RetrospectRepository,
    private val recommendRetrospectRepository: RecommendRetrospectRepository
) : GetCategoryRetrospectPort {
    override fun getRetrospectListByCategoryIdentifierWithPageToken(pageToken: String, categoryIdentifier: String, size: Int): RetrospectList {

        val parsedPageToken = cryptoHandler.decrypt(pageToken, PageTokenSearchRetrospect::class)

        val result = if (categoryIdentifier == "all") {
            retrospectRepository.findAllByCreatedAtBefore(
                lastEntityId = parsedPageToken.lastEntityId,
                lastEntityCreatedAt = parsedPageToken.lastEntityCreatedAt,
                size = size + 1
            )
        } else {
            retrospectRepository.findAllByCreatedAtBeforeAndCategoryIdentifier(
                lastEntityId = parsedPageToken.lastEntityId,
                lastEntityCreatedAt = parsedPageToken.lastEntityCreatedAt,
                categoryIdentifier = categoryIdentifier,
                size = size + 1
            )
        }

        val resultList = result.subList(0, minOf(size, result.size))

        val nextPageToken = resultList.lastOrNull()?.let { lastEntity ->
            generatePageToken(
                condition = result.size == size + 1,
                lastEntity = lastEntity
            )
        }
        return RetrospectList(
            retrospectList = result,
            nextPageToken = nextPageToken
        )
    }
    override fun getRetrospectListByCategoryIdentifier(categoryIdentifier: String, size: Int): RetrospectList {
        val result = if (categoryIdentifier == "all") {
            retrospectRepository.findAllWithSize(
                size = size + 1
            )
        } else {
            retrospectRepository.findByCategoryIdentifierWithSize(
                categoryIdentifier = categoryIdentifier,
                size = size + 1
            )
        }

        return RetrospectList(
            retrospectList = result,
            nextPageToken = null
        )
    }

    override fun getRandom(size: Int): List<Identifier> {
        return recommendRetrospectRepository.findByRandom(size).map { Identifier(it.retrospectIdentifier) }
    }

    override fun getByCategoryIdentifier(categoryIdentifier: String): List<Identifier> {
        return recommendRetrospectRepository.findByCategoryIdentifierOrderByCreatedAtDesc(
            categoryIdentifier, PageRequest.ofSize(3)
        ).map { Identifier(it.retrospectIdentifier) }
    }

    override fun getRetrospectListByIdentifiers(retrospectIdentifiers: List<Identifier>): RetrospectList {
        val result = retrospectIdentifiers.map { retrospectRepository.findOneByRetrospectIdentifier(it.value) }
        return RetrospectList(
            retrospectList = result,
            nextPageToken = null
        )
    }

    private fun generatePageToken(
        condition: Boolean,
        lastEntity: RetrospectEntity?,
    ): String? {
        return if (condition) {
            lastEntity!!
            cryptoHandler.encrypt(
                PageTokenSearchRetrospect(
                    lastEntity.id.toInt(),
                    lastEntity.createdAt
                )
            )
        } else {
            null
        }
    }

    private data class PageTokenSearchRetrospect(
        val lastEntityId: Int,
        val lastEntityCreatedAt: Timestamp,
    )
}
