package com.tenmm.tilserver.retrospect.adapter.outbound

import com.tenmm.tilserver.retrospect.application.outbound.GetCategoryRetrospectPort
import com.tenmm.tilserver.retrospect.adapter.outbound.model.RetrospectListWithPageToken
import com.tenmm.tilserver.retrospect.adapter.outbound.model.RetrospectList
import com.tenmm.tilserver.outbound.persistence.repository.RetrospectQnaRepository
import com.tenmm.tilserver.outbound.persistence.repository.RetrospectRepository
import org.springframework.stereotype.Component
import java.sql.Timestamp

import com.tenmm.tilserver.outbound.persistence.entity.RetrospectEntity

import com.tenmm.tilserver.common.utils.CryptoHandler

@Component
class GetCategoryRetrospectAdapter(
    private val cryptoHandler: CryptoHandler,
    private val retrospectQnaRepository: RetrospectQnaRepository,
    private val retrospectRepository: RetrospectRepository,
) : GetCategoryRetrospectPort {
    override fun getRetrospectListByTypeWithPageToken(pageToken: String, retrospectType: String, size: Int): RetrospectListWithPageToken {

        val parsedPageToken = cryptoHandler.decrypt(pageToken, PageTokenSearchRetrospect::class)

        val result = if (retrospectType == "all") {
            retrospectRepository.findAllByCreatedAtBefore(
                lastEntityId = parsedPageToken.lastEntityId,
                lastEntityCreatedAt = parsedPageToken.lastEntityCreatedAt,
                size = size + 1
            )
        } else {
            retrospectRepository.findAllByCreatedAtBeforeAndType(
                lastEntityId = parsedPageToken.lastEntityId,
                lastEntityCreatedAt = parsedPageToken.lastEntityCreatedAt,
                type = retrospectType,
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
        return RetrospectListWithPageToken(
            retrospectList = result,
            nextPageToken = nextPageToken
        )
    }
    override fun getRetrospectListByType(retrospectType: String, size: Int): RetrospectList {
        val result = if (retrospectType == "all") {
            retrospectRepository.findAllWithSize(
                size = size + 1
            )
        } else {
            retrospectRepository.findByTypeWithSize(
                type = retrospectType,
                size = size + 1
            )
        }

        return RetrospectList(
            retrospectList = result,
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
