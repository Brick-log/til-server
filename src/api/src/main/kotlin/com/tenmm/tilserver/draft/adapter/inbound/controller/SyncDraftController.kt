package com.tenmm.tilserver.draft.adapter.inbound.controller

import com.tenmm.tilserver.draft.adapter.inbound.controller.model.SyncDraftRequest
import com.tenmm.tilserver.draft.adapter.inbound.controller.model.SyncDraftResponse
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
// 추후 redis 사용 시 SyncDraftUseCase로 변경
import com.tenmm.tilserver.draft.application.inbound.SaveDraftUseCase
import com.tenmm.tilserver.common.domain.Identifier

@RestController
@RequestMapping("/v1/my/draft")
class SyncDraftController(
    private val saveDraftUseCase: SaveDraftUseCase,
) {
    @PutMapping("/sync")
    fun syncDraft(
        @RequestBody syncDraftRequest: SyncDraftRequest,
    ): SyncDraftResponse {
        saveDraftUseCase.save(
            Identifier.generate(),
            syncDraftRequest.data
        )

        return SyncDraftResponse(true)
    }
}
