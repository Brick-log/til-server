package com.tenmm.tilserver.draft.adapter.inbound

import com.tenmm.tilserver.draft.adapter.inbound.model.GetDraftResponse
import org.apache.commons.lang3.RandomStringUtils
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.UUID

@RestController
@RequestMapping("/v1/my/draft")
class GetDraftController {
    @GetMapping("/{draftId}")
    fun getDraft(
        @PathVariable draftId: String,
    ): GetDraftResponse {
        return GetDraftResponse(
            id = UUID.randomUUID().toString(),
            data = RandomStringUtils.random(100),
            updatedAt = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)
        )
    }
}
