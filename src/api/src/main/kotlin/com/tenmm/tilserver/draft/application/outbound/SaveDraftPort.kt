package com.tenmm.tilserver.draft.application.outbound

interface SaveDraftPort {
    fun saveDraft(userIdentifier: Identifier, data: String)
}