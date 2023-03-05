package com.tenmm.tilserver.draft.adapter.inbound.controller

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.draft.application.inbound.SyncDraftUseCase
import com.tenmm.tilserver.draft.adapter.inbound.controller.model.SyncDraftRequest
import com.tenmm.tilserver.draft.adapter.inbound.controller.model.SyncDraftResponse
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/my/draft")
class SyncDraftController(
    private val syncDraftUseCase: SyncDraftUseCase,
) {
    @PutMapping("/sync")
    fun syncDraft(
        @RequestBody syncDraftRequest: SyncDraftRequest,
    ): SyncDraftResponse {
        syncDraftUseCase.sync(
            Identifier("913115be-5b64-491e-bcfb-d5e724f25642"), // TODO token에서 가져오도록 수정
            syncDraftRequest.data
        )
        return SyncDraftResponse(true)
        /**
         * Exception : IllegalArgumentException => 400, Identifier가 잘못된 경우
         */
    }
}
