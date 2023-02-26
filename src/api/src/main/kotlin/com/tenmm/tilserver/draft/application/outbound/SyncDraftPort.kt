package com.tenmm.tilserver.draft.application.outbound

interface SaveDraftPort {
    fun syncDraft(userIdentifier: Identifier, data: String)
}