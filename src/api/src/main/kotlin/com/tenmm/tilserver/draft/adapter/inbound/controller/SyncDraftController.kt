package com.tenmm.tilserver.draft.adapter.inbound.controller

import com.tenmm.tilserver.draft.adapter.inbound.controller.model.SyncDraftRequest
import com.tenmm.tilserver.draft.adapter.inbound.controller.model.SyncDraftResponse
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/my/draft")
class SyncDraftController {
    @PutMapping("/sync")
    fun syncDraft(
        @RequestBody syncDraftRequest: SyncDraftRequest,
    ): SyncDraftResponse {
        return SyncDraftResponse(true)
    }
}
