package com.tenmm.tilserver.draft.application.outbound

interface GetDraftPort {
    fun getDraft(userIdentifier: Identifier): Draft
}