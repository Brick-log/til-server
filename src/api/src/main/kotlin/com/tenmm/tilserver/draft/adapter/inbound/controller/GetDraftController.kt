package com.tenmm.tilserver.draft.adapter.inbound.controller

import com.tenmm.tilserver.common.domain.Identifier
import com.tenmm.tilserver.draft.domain.Draft
import com.tenmm.tilserver.draft.application.inbound.GetDraftUseCase
import com.tenmm.tilserver.draft.adapter.inbound.controller.model.GetDraftResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/my/draft")
class GetDraftController(
    private val getDraftUseCase: GetDraftUseCase,
) {
    @GetMapping
    fun getDraft(): GetDraftResponse {
        val draft: Draft? = getDraftUseCase.getByUserIdentifier(Identifier("913115be-5b64-491e-bcfb-d5e724f25642")) // TODO token에서 가져오도록 수정
        return GetDraftResponse(
            data = draft?.data,
            updatedAt = draft?.updatedAt,
        )
        /**
         * Exception : IllegalArgumentException => 400, Identifier가 잘못된 경우
         */
    }
}
