package com.tenmm.tilserver.draft.adapter.inbound

import com.tenmm.tilserver.draft.adapter.inbound.model.SyncDraftRequest
import com.tenmm.tilserver.draft.adapter.inbound.model.SyncDraftResponse
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/my/draft")
class SyncDraftController {
    @PutMapping("/sync")
    fun getDraft(
        @RequestBody syncDraftRequest: SyncDraftRequest,
    ): SyncDraftResponse {
        return SyncDraftResponse(true)
    }
}
