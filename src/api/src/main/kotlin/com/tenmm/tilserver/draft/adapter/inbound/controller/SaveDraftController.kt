package com.tenmm.tilserver.draft.adapter.inbound.controller

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.draft.adapter.inbound.controller.model.SaveDraftRequest
import com.tenmm.tilserver.draft.adapter.inbound.controller.model.SaveDraftResponse
import com.tenmm.tilserver.draft.application.inbound.SaveDraftUseCase
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/my/draft")
class SaveDraftController(
    private val saveDraftUseCase: SaveDraftUseCase,
) {
    @PostMapping
    fun save(
        @RequestBody saveDraftRequest: SaveDraftRequest,
    ): SaveDraftResponse {
        saveDraftUseCase.saveByUserIdentifier(
            Identifier("913115be-5b64-491e-bcfb-d5e724f25642"), // TODO token에서 가져오도록 수정
            saveDraftRequest.data
        )
        return SaveDraftResponse(true)
        /**
         * Exception : IllegalArgumentException => 400, Identifier가 잘못된 경우
         */
    }
}
