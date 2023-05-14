package com.tenmm.tilserver.draft.adapter.outbound

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.draft.application.outbound.SyncDraftPort
import com.tenmm.tilserver.draft.domain.Draft
import mu.KotlinLogging
import org.springframework.stereotype.Component
import com.tenmm.tilserver.outbound.persistence.entity.SyncDraftEntity
import com.tenmm.tilserver.outbound.persistence.repository.SyncDraftRepository
import org.springframework.data.domain.PageRequest

private val logger = KotlinLogging.logger {}
@Component
class SyncDraftAdapter(
    private val syncDraftRepository: SyncDraftRepository,
) : SyncDraftPort {

    override suspend fun save(userIdentifier: Identifier, data: String): Boolean {
        return this.syncDraftRepository.save(
            SyncDraftEntity(
                userIdentifier = userIdentifier.value,
                data = data
            )
        ) != null
    }

    override suspend fun findByUserIdentifier(userIdentifier: Identifier): Draft? {
        return this.syncDraftRepository.findByUserIdentifier(userIdentifier.value)?.let {
            Draft(
                userIdentifier = Identifier(it.userIdentifier),
                data = it.data
            )
        }
    }

    override suspend fun findDraftsWithCount(size: Int): List<Draft> {
        // get draft with count
        val pageRequest = PageRequest.of(0, size)
        this.syncDraftRepository.findAll(pageRequest).let { syncDraftEntityList ->
            if (syncDraftEntityList.isEmpty()) {
                return emptyList()
            }

            return syncDraftEntityList.map {
                Draft(
                    userIdentifier = Identifier(it.userIdentifier),
                    data = it.data
                )
            }.toList()
        }
    }

    override suspend fun delete(draft: Draft): Boolean {
        return this.syncDraftRepository.findByUserIdentifier(draft.userIdentifier.value).let(
            fun(syncDraftEntity: SyncDraftEntity?): SyncDraftEntity? {
                return if (syncDraftEntity != null) {
                    this.syncDraftRepository.delete(syncDraftEntity)
                    syncDraftEntity
                } else {
                    null
                }
            }
        ) != null
    }

    override suspend fun delete(drafts: List<Draft>): Boolean {
        if (drafts.isEmpty()) {
            logger.info("Delete Sync Draft Target Size: Empty")
            return true
        }

        val keys = drafts.map { draft -> this.delete(draft) }
        val deletedKeySize = keys.filter { it }.size

        logger.info("Delete Sync Draft Target Size: $deletedKeySize")

        return deletedKeySize == drafts.size
    }
}
