package com.tenmm.tilserver.draft.adapter.inbound.controller

import com.tenmm.tilserver.draft.adapter.inbound.controller.model.GetDraftResponse
import java.time.LocalDateTime
import java.time.ZoneOffset
// import org.apache.commons.lang3.RandomStringUtils
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/my/draft")
class GetDraftController {
    @GetMapping
    fun getDraft(): GetDraftResponse {
        return GetDraftResponse(
            data = "Mock data from [/v1/my/draft]",
            updatedAt = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)
        )
    }
}
