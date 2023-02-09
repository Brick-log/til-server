package com.tenmm.tilserver.draft.adapter.inbound.controller

import com.tenmm.tilserver.draft.adapter.inbound.controller.model.SaveDraftRequest
import com.tenmm.tilserver.draft.adapter.inbound.controller.model.SaveDraftResponse
import com.tenmm.tilserver.draft.application.inbound.SaveDraftUseCase
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/my/draft")
class SaveDraftController(
    private val saveDraftUseCase: SaveDraftUseCase
) {
    @PostMapping
    fun save(saveDraftRequest: SaveDraftRequest): SaveDraftResponse {
        saveDraftUseCase.save(
            saveDraftRequest.draftId,
            saveDraftRequest.data
        )

        return SaveDraftResponse(true)
    }
}
